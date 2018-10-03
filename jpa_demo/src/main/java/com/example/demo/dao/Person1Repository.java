package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Person1;
@Repository
public interface Person1Repository extends JpaRepository<Person1, Integer> {
}
