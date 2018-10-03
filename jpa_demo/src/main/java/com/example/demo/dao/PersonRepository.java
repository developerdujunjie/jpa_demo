package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	List<Person> findByName(String name);
	
	List<Person> findByAddress_code(String code);
}
