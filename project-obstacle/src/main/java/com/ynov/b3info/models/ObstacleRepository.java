package com.ynov.b3info.models;

import org.springframework.data.repository.CrudRepository;

public interface ObstacleRepository extends CrudRepository<Obstacle, Integer> {

	public Iterable<Obstacle> searchObstaclesByName(String name);
}
