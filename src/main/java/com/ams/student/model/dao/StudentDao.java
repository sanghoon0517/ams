package com.ams.student.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ams.student.model.dto.StudentDto;

@Mapper
public interface StudentDao {
	int insertStudent(StudentDto vo);
}
