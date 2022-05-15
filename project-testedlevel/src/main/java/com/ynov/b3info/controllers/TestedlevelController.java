package com.ynov.b3info.controllers;

import java.util.Optional;

import com.ynov.b3info.models.Testedlevel;
import com.ynov.b3info.repositories.TestedlevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class TestedlevelController {

	@Autowired
	private TestedlevelRepository testedlevelRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Testedlevel>> getTestedlevels() {
		return ResponseEntity.ok(testedlevelRepository.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Testedlevel>> getTestedlevel(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(testedlevelRepository.findById(id));
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Testedlevel> deleteTestedlevel(@PathVariable("id") Integer id) {
		Optional<Testedlevel> optTestedlevel = testedlevelRepository.findById(id);
		if (optTestedlevel.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Testedlevel testedlevel = optTestedlevel.get();
		testedlevelRepository.delete(testedlevel);
		return ResponseEntity.ok(testedlevel);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Testedlevel> updateTestedlevel(@PathVariable("id") Integer id, @RequestBody Testedlevel testedlevel) {
		Optional<Testedlevel> otpTestedlevel = testedlevelRepository.findById(id);
		if (otpTestedlevel.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Testedlevel newTestedlevel = otpTestedlevel.get();
		newTestedlevel.setLevelId(testedlevel.getLevelId());
		newTestedlevel.setDate(testedlevel.getDate());
		newTestedlevel.setResult(testedlevel.getResult());
		return ResponseEntity.ok(testedlevelRepository.save(newTestedlevel));
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Testedlevel> createTestedlevel(@RequestBody Testedlevel testedlevel) {
		Testedlevel newTestedlevel = new Testedlevel();
		newTestedlevel.setLevelId(testedlevel.getLevelId());
		newTestedlevel.setDate(testedlevel.getDate());
		newTestedlevel.setResult(testedlevel.getResult());
		return ResponseEntity.ok(testedlevelRepository.save(newTestedlevel));
	}
}
