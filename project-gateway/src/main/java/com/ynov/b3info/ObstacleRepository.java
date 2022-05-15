package com.ynov.b3info;

import java.util.Optional;

import com.ynov.b3info.Model.Obstacle;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/api/obstacle", name = "PROJECT-OBSTACLE")
public interface ObstacleRepository {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Obstacle>> getObstacles();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Obstacle>> getObstacle(@PathVariable("id") Integer id);

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Obstacle>> searchObstaclesByName(@PathVariable("name") String name);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Obstacle> updateObstacle(@PathVariable("id") Integer id, @RequestBody Obstacle newObstacle);

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Obstacle> createObstacle(@RequestBody Obstacle newObstacle);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Obstacle> deleteObstacle(@PathVariable("id") Integer id);
	
}
