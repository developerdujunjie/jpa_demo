package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.ClassRoom;

public interface ClassroomRepository extends JpaRepository<ClassRoom, Integer> {

}
