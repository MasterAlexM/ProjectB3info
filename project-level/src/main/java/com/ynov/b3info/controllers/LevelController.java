package com.ynov.b3info.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.b3info.models.Level;
import com.ynov.b3info.repositories.LevelRepository;

@RestController
@RequestMapping("api/level")
public class LevelController {

	@Autowired
	private LevelRepository levelRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Level>> getLevels() {
		return ResponseEntity.ok(levelRepository.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Level>> getLevel(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(levelRepository.findById(id));
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Level>> searchLevelsByCreator(@PathVariable("name") String name) {
		return ResponseEntity.ok(levelRepository.searchLevelsByCreator(name));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Level> deleteLevel(@PathVariable("id") Integer id) {
		Optional<Level> optLevel = levelRepository.findById(id);
		if (optLevel.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Level level = optLevel.get();
		levelRepository.delete(level);
		return ResponseEntity.ok(level);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Level> updateLevel(@PathVariable("id") Integer id, @RequestBody Level level) {
		Optional<Level> otpLevel = levelRepository.findById(id);
		if (otpLevel.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Level newLevel = otpLevel.get();
		newLevel.setComposition(level.getComposition());
		newLevel.setCreatedAt(level.getCreatedAt());
		newLevel.setCreator(level.getCreator());
		newLevel.setModifiedAt(level.getModifiedAt());
		newLevel.setName(level.getName());
		return ResponseEntity.ok(levelRepository.save(newLevel));
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Level> createLevel(@RequestBody Level level) {
		Level newLevel = new Level();
		newLevel.setComposition(level.getComposition());
		newLevel.setCreatedAt(level.getCreatedAt());
		newLevel.setCreator(level.getCreator());
		newLevel.setModifiedAt(level.getModifiedAt());
		newLevel.setName(level.getName());
		return ResponseEntity.ok(levelRepository.save(newLevel));
	}
}
