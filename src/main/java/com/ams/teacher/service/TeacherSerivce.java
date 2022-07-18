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
	
	/**
	 * 선생님 한명 정보 불러오기
	 * @param t_id
	 * @return
	 */
	public TeacherDto selectOneDao(String t_id);

	/**
	 * 회원 가입 요청
	 * @param dto
	 */
	public void insertDao(TeacherDto dto);

	
	/**
	 * 아이디 유효성 검사
	 * @param t_id
	 * @return 계정 수 반환 1이상이면 이미 가입된 ID
	 */
	public int checkIdDao(String t_id);
}
