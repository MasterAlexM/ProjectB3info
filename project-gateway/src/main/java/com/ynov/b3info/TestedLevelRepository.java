package com.ynov.b3info;

import java.util.Optional;

import com.ynov.b3info.Model.TestedLevel;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/api/testedlevel", name = "PROJECT-TESTEDLEVEL")
public interface TestedLevelRepository {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<TestedLevel>> getTestedLevels();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<TestedLevel>> getTestedLevel(@PathVariable("id") Integer id);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<TestedLevel> updateTestedLevel(@PathVariable("id") Integer id, @RequestBody TestedLevel newTestedLevel);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<TestedLevel> deleteTestedLevel(@PathVariable("id") Integer id);

	@RequestMapping(value = "/testedlevel", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<TestedLevel> createTestedLevel(@RequestBody TestedLevel newTestedLevel);
	
}
