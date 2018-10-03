package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.po.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
