using UnityEngine;

enum ToolType
{
    NONE,
    ERASER
}

[CreateAssetMenu(fileName = "Tool", menuName = "LevelBuilding/Create Tool")]
public class BuildingTool : BuildingObjectBase
{
    [SerializeField] private ToolType toolType;
    ToolController tc;

    void Awake()
    {
        tc = ToolController.GetInstance();
    }

    public void Use(Vector3Int position)
    {
        switch (toolType)
        {
            case ToolType.ERASER:
                tc.Eraser(position);
                break;
            default:
                Debug.LogError("Tool Type not set");
                break;
        }
    }
}