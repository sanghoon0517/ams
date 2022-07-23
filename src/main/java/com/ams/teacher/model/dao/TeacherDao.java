package com.ams.teacher.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ams.teacher.model.dto.TeacherDto;

@Mapper
public interface TeacherDao {
	public List<TeacherDto> listDao();
	public int insertDao(TeacherDto dto);
	public TeacherDto selectOneDao(String t_id);
	public int checkIdDao(String t_id);
}
