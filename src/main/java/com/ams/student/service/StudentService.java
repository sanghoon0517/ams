package com.ams.student.service;

import java.util.List;

import com.ams.student.model.dto.StudentDto;

public interface StudentService {
	
	public int enrollStudent(StudentDto vo);
	public String getStdDate();
	public List<StudentDto> getStudentList();
}
