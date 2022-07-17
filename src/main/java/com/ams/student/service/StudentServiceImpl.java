package com.ams.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.student.model.dao.StudentDao;
import com.ams.student.model.dto.StudentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{
	
	private final StudentDao studentDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int enrollStudent(StudentDto vo) {
		// TODO Auto-generated method stub
		int result = studentDao.insertStudent(vo);
		return result;
	}

}
