package com.ams.student.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ams.common.model.dto.PaginationCriteriaDto;
import com.ams.student.model.dto.StudentDto;

@Mapper
public interface StudentDao {
	int insertStudent(StudentDto vo);
	String getStdDate();
	List<StudentDto> getStudentList();
	List<StudentDto> getStudentListPaging(PaginationCriteriaDto pagingParam);
	int getStudentListCount();
	int getStudentListCountParam(String search);
	int chkStudentInfoCount(StudentDto vo);
	StudentDto getStudnetInfoByIdx(int st_idx);
	int modifyStudentInfo(StudentDto vo);
	int deleteStudent(int st_idx);
}
