//PathFinder A*

using System;
using System.Collections.Generic;


// A* needs only a WeightedGraph and a location type L, and does *not*
// have to be a grid. However, in the example code I am using a grid.
public interface WeightedGraph<L>
{
    double Cost(Location a, Location b);
    IEnumerable<Location> Neighbors(Location id);
}


public struct Location
{
    // Implementation notes: I am using the default Equals but it can
    // be slow. You'll probably want to override both Equals and
    // GetHashCode in a real project.
    
    public readonly int x, y;
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}


public class SquareGrid : WeightedGraph<Location>
{
    // Implementation notes: I made the fields public for convenience,
    // but in a real project you'll probably want to follow standard
    // style and make them private.
    
    public static readonly Location[] DIRS = new []
        {
            new Location(1, 0),
            new Location(0, -1),
            new Location(-1, 0),
            new Location(0, 1)
        };

    public int width, height;
    public HashSet<Location> walls = new HashSet<Location>();
    public HashSet<Location> traps = new HashSet<Location>();
    public HashSet<Location> muds = new HashSet<Location>();

    public SquareGrid(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public bool InBounds(Location id)
    {
        return 0 <= id.x && id.x < width
            && 0 <= id.y && id.y < height;
    }

    public bool Passable(Location id)
    {
        return (!walls.Contains(id) || !traps.Contains(id));
    }

    public double Cost(Location a, Location b)
    {
        return muds.Contains(b) ? 3 : 1;
    }
    
    public IEnumerable<Location> Neighbors(Location id)
    {
        foreach (var dir in DIRS) {
            Location next = new Location(id.x + dir.x, id.y + dir.y);
            if (InBounds(next) && Passable(next)) {
                yield return next;
            }
        }
    }
}


public class PriorityQueue<T>
{
    
    private List<Tuple<T, double>> elements = new List<Tuple<T, double>>();

    public int Count
    {
        get { return elements.Count; }
    }
    
    public void Enqueue(T item, double priority)
    {
        elements.Add(Tuple.Create(item, priority));
    }

    public T Dequeue()
    {
        int bestIndex = 0;

        for (int i = 0; i < elements.Count; i++) {
            if (elements[i].Item2 < elements[bestIndex].Item2) {
                bestIndex = i;
            }
        }

        T bestItem = elements[bestIndex].Item1;
        elements.RemoveAt(bestIndex);
        return bestItem;
    }
}


/* NOTE about types: in the main article, in the Python code I just
 * use numbers for costs, heuristics, and priorities. In the C++ code
 * I use a typedef for this, because you might want int or double or
 * another type. In this C# code I use double for costs, heuristics,
 * and priorities. You can use an int if you know your values are
 * always integers, and you can use a smaller size number if you know
 * the values are always small. */

public class AStarSearch
{
    public Dictionary<Location, Location> cameFrom
        = new Dictionary<Location, Location>();
    public Dictionary<Location, double> costSoFar
        = new Dictionary<Location, double>();

    // Note: a generic version of A* would abstract over Location and
    // also Heuristic
    static public double Heuristic(Location a, Location b)
    {
        return Math.Abs(a.x - b.x) + Math.Abs(a.y - b.y);
    }

    public AStarSearch(WeightedGraph<Location> graph, Location start, Location goal)
    {
        var frontier = new PriorityQueue<Location>();
        frontier.Enqueue(start, 0);

        cameFrom[start] = start;
        costSoFar[start] = 0;

        while (frontier.Count > 0)
        {
            var current = frontier.Dequeue();

            if (current.Equals(goal))
            {
                break;
            }

            foreach (var next in graph.Neighbors(current))
            {
                double newCost = costSoFar[current]
                    + graph.Cost(current, next);
                if (!costSoFar.ContainsKey(next)
                    || newCost < costSoFar[next])
                {
                    costSoFar[next] = newCost;
                    double priority = newCost + Heuristic(next, goal);
                    frontier.Enqueue(next, priority);
                    cameFrom[next] = current;
                }
            }
        }
    }
}

public class Test
{
    static int[,] DrawGrid(SquareGrid grid, AStarSearch astar) {
        int[,] result = new int[grid.height,grid.width];
        // Print out the cameFrom array
        for (var y = 0; y < grid.height; y++)
        {
            for (var x = 0; x < grid.width; x++)
            {
                Location id = new Location(x, y);
                Location ptr = id;
                if (!astar.cameFrom.TryGetValue(id, out ptr))
                {
                    ptr = id;
                }

                if (grid.walls.Contains(id)) { result[x,y] = 0; }
                else if (ptr.x == x+1) { result[x,y] = 1; }
                else if (ptr.x == x-1) { result[x,y] = 1; }
                else if (ptr.y == y+1) { result[x,y] = 1; }
                else if (ptr.y == y-1) { result[x,y] = 1; }
                else { result[x,y] = 0; }
            }
        }
        return result;
    }
    
    static int[,] Main(int[ ,] laby, Location start, Location stop)
    {
        var grid = new SquareGrid(laby.GetLength(0),laby.GetLength(1));
        for (var x = 0; x < grid.height; x++)
        {
            for (var y = 0; y < grid.width; y++)
            {
                if(laby[x,y] == 1)
                {
                    grid.walls.Add(new Location(x,y));
                }
                else if(laby[x,y] == 2)
                {
                    grid.muds.Add(new Location(x,y));
                }
                else if(laby[x,y] == 2)
                {
                    grid.traps.Add(new Location(x,y));
                }
            }
        }

        // Run A*
        var astar = new AStarSearch(grid, start, stop);

        return DrawGrid(grid, astar);
    }
}