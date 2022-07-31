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
}
