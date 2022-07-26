package com.ams.classes.service;

import java.util.HashMap;

import com.ams.classes.model.dto.ClassDto;

public interface ClassService {

    public int insertClass(ClassDto dto);
    public int updateStClass(HashMap<String,String> map);
}
