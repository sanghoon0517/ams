package com.ams.teacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.teacher.model.dao.TeacherDao;
import com.ams.teacher.model.dto.TeacherDto;

public interface TeacherSerivce{
	/**
	 * 선생 리스트 조회
	 * @return 선생님 리스트 값
	 */
	public List<TeacherDto> listDao();

	public TeacherDto selectOneDao(String t_id);

	public void insertDao(TeacherDto dto);

}
