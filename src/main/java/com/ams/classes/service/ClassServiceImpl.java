package com.ams.classes.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.classes.model.dao.ClassDao;
import com.ams.classes.model.dto.ClassDto;

@Service
@Transactional
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassDao dao;
    @Override
    public int insertClass(ClassDto dto) {
        int result = dao.insertClass(dto);

        return result;
    }

    @Override
    public int updateStClass(HashMap<String, String> map) {
        int result = dao.updateStClass(map);
        return result;
    }
    
}
