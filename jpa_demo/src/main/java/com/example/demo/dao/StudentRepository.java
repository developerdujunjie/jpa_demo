package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.Student;
import com.example.demo.dto.IStudentDTO;
import com.example.demo.dto.StudentDTO2;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	@Query(value="select s.name as studentName,s.classRoom.id as classroomId,s.classRoom.name as classroomName from Student s where s.id=?1")
	public IStudentDTO getIStudentDTOById(Integer id);
	
	public <T> T  findStudentNameById(Integer id,Class<T> clazz);
}
