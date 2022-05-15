package com.ynov.b3info.repositories;

import com.ynov.b3info.models.Level;

import org.springframework.data.repository.CrudRepository;



public interface LevelRepository extends CrudRepository<Level, Integer> {

	Iterable<Level> searchLevelsByCreator(String name);

}
