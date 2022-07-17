package com.ams.teacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.teacher.model.dao.TeacherDao;
import com.ams.teacher.model.dto.TeacherDto;


@Service
@Transactional
public class TeacherSerivceImpl implements TeacherSerivce{

	@Autowired
	TeacherDao dao;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<TeacherDto> listDao(){
		List<TeacherDto> list= dao.listDao();
		return list;
	}

	@Override
	public TeacherDto selectOneDao(String t_id) {

		TeacherDto dto = dao.selectOneDao(t_id);
		return dto;
	}

	@Override
	public void insertDao(TeacherDto dto) {
		dao.insertDao(dto);
	}

	@Override
	public int checkIdDao(String t_id) {
		int result =dao.checkIdDao(t_id);
		return result;
	}


	
}
