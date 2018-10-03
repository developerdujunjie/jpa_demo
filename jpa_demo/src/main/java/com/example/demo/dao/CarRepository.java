package com.example.demo.dao;

import java.util.List;

public interface CarRepository extends BaseRepository<Car> {
	
	List<Car> findByCarName(String name1);

}
