package com.ams.classes.service;

import java.util.HashMap;
import java.util.List;

import com.ams.classes.model.dto.ClassDto;

public interface ClassService {

    public int insertClass(ClassDto dto);
    public int updateStClass(HashMap<String,String> map);
    public List<ClassDto> getAllClasses();
    public String numToDay(int c_wkd);
    public int countStClass(int c_idx);
}
