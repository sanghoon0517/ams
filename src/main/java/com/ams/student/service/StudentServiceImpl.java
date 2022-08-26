package com.ams.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.common.model.dto.PaginationCriteriaDto;
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

	@Transactional(readOnly = true)
	@Override
	public String getStdDate() {
		// TODO Auto-generated method stub
		return studentDao.getStdDate();
	}

	@Transactional(readOnly = true)
	@Override
	public List<StudentDto> getStudentList() {
		// TODO Auto-generated method stub
		return studentDao.getStudentList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<StudentDto> getStudentListPaging(PaginationCriteriaDto pagingparam) {
		// TODO Auto-generated method stub
		return studentDao.getStudentListPaging(pagingparam);
	}

	@Transactional(readOnly = true)
	@Override
	public int getStudentListCount() {
		// TODO Auto-generated method stub
		return studentDao.getStudentListCount();
	}

	@Transactional(readOnly = true)
	@Override
	public StudentDto getStudentInfoByIdx(int st_idx) {
		// TODO Auto-generated method stub
		return studentDao.getStudnetInfoByIdx(st_idx);
	}

	@Transactional(readOnly = true)
	@Override
	public int getStudentListCountParam(String search) {
		// TODO Auto-generated method stub
		return studentDao.getStudentListCountParam(search);
	}

	@Transactional(readOnly = true)
	@Override
	public int chkStudentInfoCount(StudentDto vo) {
		// TODO Auto-generated method stub
		return studentDao.chkStudentInfoCount(vo);
	}

	@Override
	public int modifyStudentInfo(StudentDto vo) {
		// TODO Auto-generated method stub
		return studentDao.modifyStudentInfo(vo);
	}

	@Override
	public int deleteStudent(int st_idx) {
		// TODO Auto-generated method stub
		return studentDao.deleteStudent(st_idx);
	}
	
}
