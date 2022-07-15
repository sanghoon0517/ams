package com.ams.teacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.teacher.model.dao.TeacherDao;
import com.ams.teacher.model.dto.TeacherDto;

@Service
@Transactional
public class TeacherSerivceImpl implements TeacherSerivce{

	@Autowired
	TeacherDao dao;
	
	@Override
	public List<TeacherDto> listDao() {
		List<TeacherDto> list= dao.listDao();
		return list;
	}
	
}
