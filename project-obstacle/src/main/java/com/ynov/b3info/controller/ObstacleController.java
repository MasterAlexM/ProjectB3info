package com.ynov.b3info.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.b3info.models.Obstacle;
import com.ynov.b3info.models.ObstacleRepository;

@RestController
@RequestMapping("/api/obstacle")
public class ObstacleController {
	
	@Autowired
	private ObstacleRepository obstacleRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Obstacle>> getObstacles() {
		return ResponseEntity.ok(obstacleRepository.findAll());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Obstacle>> getObstacle(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(obstacleRepository.findById(id));
	}
	

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Obstacle>> searchObstaclesByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(obstacleRepository.searchObstaclesByName(name));
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Obstacle> createObstacle(@RequestBody Obstacle obstacle) {
		Obstacle newObstacle = new Obstacle();
		newObstacle.setName(obstacle.getName());
		newObstacle.setEffectId(obstacle.getEffectId());
		newObstacle.setAppearanceId(obstacle.getAppearanceId());
		newObstacle.setMaxInLevel(obstacle.getMaxInLevel());
		newObstacle.setMinInLevel(obstacle.getMinInLevel());
		newObstacle.setTraversables(obstacle.isTraversables());
		return ResponseEntity.ok(obstacleRepository.save(newObstacle));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Obstacle> updateObstacle(@PathVariable("id") Integer id, @RequestBody Obstacle obstacle) {
		Optional<Obstacle> optObstacle = obstacleRepository.findById(id);
		if (optObstacle.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Obstacle newObstacle = optObstacle.get();
		newObstacle.setName(obstacle.getName());
		newObstacle.setEffectId(obstacle.getEffectId());
		newObstacle.setAppearanceId(obstacle.getAppearanceId());
		newObstacle.setMaxInLevel(obstacle.getMaxInLevel());
		newObstacle.setMinInLevel(obstacle.getMinInLevel());
		newObstacle.setTraversables(obstacle.isTraversables());
		return ResponseEntity.ok(obstacleRepository.save(obstacle));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Obstacle> deleteObstacle(@PathVariable("id") Integer id) {
		Optional<Obstacle> optObstacle = obstacleRepository.findById(id);
		if (optObstacle.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Obstacle obstacle = optObstacle.get();
		obstacleRepository.delete(obstacle);
		return ResponseEntity.ok(obstacle);
	}
}
