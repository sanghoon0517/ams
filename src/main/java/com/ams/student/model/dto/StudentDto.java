package com.ams.student.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()
public class StudentDto {
	private int st_idx;		//학생idx
	private int c_idx;			//클래스idx
	private String st_nm;		//학생이름
	private String st_bth;		//학생생년월일
	private Gender st_gen;		//학생성별
	private String st_pn;		//학생폰번호
	private String st_en_dt;	//학생등록일자
	private String st_dis_dt;	//학생퇴원일자
	private String st_prt_nm;	//학생부모명
	private String st_prt_pn;	//학생부모연락처
	private String st_adr;		//학생주소
	private String st_dtl;		//학생당부사항
	private int st_rm_c_cnt;	//학생남은수업횟수
}
