package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class ClassroomDTO {

	private String name;
	
	private List<StudentDTO> students=new ArrayList<StudentDTO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}
	
	
}
