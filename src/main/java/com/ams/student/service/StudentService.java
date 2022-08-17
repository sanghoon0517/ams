package com.ams.student.service;

import java.util.List;

import com.ams.common.model.dto.PaginationCriteriaDto;
import com.ams.student.model.dto.StudentDto;

public interface StudentService {
	
	public int enrollStudent(StudentDto vo);
	public String getStdDate();
	public List<StudentDto> getStudentList();
	public List<StudentDto> getStudentListPaging(PaginationCriteriaDto pagingParam);
	public int getStudentListCount();
	public int getStudentListCountParam(String search);
	public int chkStudentInfoCount(StudentDto vo);
	public StudentDto getStudentInfoByIdx(int st_idx);
	int modifyStudentInfo(StudentDto vo);
}
