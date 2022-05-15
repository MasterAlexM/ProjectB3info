package com.ynov.b3info;

import java.util.Optional;

import com.ynov.b3info.Model.Level;
import com.ynov.b3info.Model.Obstacle;
import com.ynov.b3info.Model.TestedLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProjectController {

    
	@Autowired
	private ObstacleRepository ObstacleRepository;
	@Autowired
	private LevelRepository LevelRepository;
    @Autowired
	private TestedLevelRepository TestedLevelRepository;

	@RequestMapping(value = "/obstacle", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Obstacle>> getObstacles() {
		return ObstacleRepository.getObstacles();
	}
	
	@RequestMapping(value = "/obstacle/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Obstacle>> getObstacle(@PathVariable("id") Integer id) {
		return ObstacleRepository.getObstacle(id);
	}

	@RequestMapping(value = "/obstacle/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Obstacle>> searchObstaclesByName(@PathVariable("name") String name) {
		return ObstacleRepository.searchObstaclesByName(name);
	}
	
	
	@RequestMapping(value = "/obstacle/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Obstacle> updateObstacle(@PathVariable("id") Integer id, @RequestBody Obstacle newObstacle) {
		return ObstacleRepository.updateObstacle(id, newObstacle);
	}

	@RequestMapping(value = "/obstacle", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Obstacle> createObstacle(@RequestBody Obstacle newObstacle) {
		return ObstacleRepository.createObstacle(newObstacle);
	}
	
	@RequestMapping(value = "/obstacle/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Obstacle> deleteObstacle(@PathVariable("id") Integer id) {
		return ObstacleRepository.deleteObstacle(id);
	}
	
	//---------------------------------------------------------------

    @RequestMapping(value = "/level", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Level>> getLevels() {
		return LevelRepository.getLevels();
	}
	
	@RequestMapping(value = "/level/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Level>> getLevel(@PathVariable("id") Integer id) {
		return LevelRepository.getLevel(id);
	}

	@RequestMapping(value = "/level/search/{creator}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Level>> searchLevelsByCreator(@PathVariable("creator") String creator) {
		return LevelRepository.searchLevelsByCreator(creator);
	}
	
	
	@RequestMapping(value = "/level/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Level> updateLevel(@PathVariable("id") Integer id, @RequestBody Level newLevel) {
		return LevelRepository.updateLevel(id, newLevel);
	}

	@RequestMapping(value = "/level", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Level> createLevel(@RequestBody Level newLevel) {
		return LevelRepository.createLevel(newLevel);
	}
	
	@RequestMapping(value = "/level/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Level> deleteLevel(@PathVariable("id") Integer id) {
		return LevelRepository.deleteLevel(id);
    }

    //---------------------------------------------------------------
	
    @RequestMapping(value = "/testedlevel", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<TestedLevel>> getTestedLevels() {
		return TestedLevelRepository.getTestedLevels();
	}
	
	@RequestMapping(value = "/testedlevel/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<TestedLevel>> getTestedLevel(@PathVariable("id") Integer id) {
		return TestedLevelRepository.getTestedLevel(id);
	}
	
	@RequestMapping(value = "/testedlevel/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<TestedLevel> updateTestedLevel(@PathVariable("id") Integer id, @RequestBody TestedLevel newTestedLevel) {
		return TestedLevelRepository.updateTestedLevel(id, newTestedLevel);
	}

	@RequestMapping(value = "/testedlevel", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<TestedLevel> createTestedLevel(@RequestBody TestedLevel newTestedLevel) {
		return TestedLevelRepository.createTestedLevel(newTestedLevel);
	}
	
	@RequestMapping(value = "/testedlevel/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<TestedLevel> deleteTestedLevel(@PathVariable("id") Integer id) {
		return TestedLevelRepository.deleteTestedLevel(id);
    }
}
