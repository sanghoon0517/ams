package com.ams.classes.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ams.classes.model.dto.ClassDto;
import com.ams.student.model.dto.StudentDto;

@Mapper
public interface ClassDao {
    public int insertClass(ClassDto vo);
    public int updateStClass(ClassDto vo);
    public List<ClassDto> getAllClasses();
    public int countStClass(int c_idx);
    public List<StudentDto> getAllSchl();
    public List<StudentDto> getStudent(ClassDto dto);
    public ClassDto getClass(int c_idx);
    public List<StudentDto> getClassStudent(int c_idx);
    public String getTeachername(int c_idx);
    public int updateClass(int c_idx);
}
