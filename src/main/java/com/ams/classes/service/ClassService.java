package com.ams.classes.service;

import java.util.HashMap;
import java.util.List;

import com.ams.classes.model.dto.ClassDto;
import com.ams.student.model.dto.StudentDto;

public interface ClassService {

    public int insertClass(ClassDto dto);
    public int updateStClass(ClassDto dto);
    public List<ClassDto> getAllClasses();
    public String numToDay(int c_wkd);
    public int countStClass(int c_idx);
    public List<StudentDto>getAllSchl();
    public List<StudentDto> getStudent(ClassDto dto);
    public ClassDto getClass(int c_idx);
    public List<StudentDto> getClassStudent(int c_idx);
    public String getTeachername(int c_idx);
    public String getKoreanAge(String st_bth);
    public String getGender(String st_gen);
    public int updateClass(int c_idx);
}
