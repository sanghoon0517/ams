package com.ams.classes.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ams.classes.model.dto.ClassDto;

@Mapper
public interface ClassDao {
    public int insertClass(ClassDto vo);
    public int updateStClass(HashMap<String,String> map);
}
