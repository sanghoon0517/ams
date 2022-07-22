package com.ams.student.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ams.student.model.dto.StudentDto;

@Mapper
public interface StudentDao {
	int insertStudent(StudentDto vo);
	String getStdDate();
	List<StudentDto> getStudentList();
}
