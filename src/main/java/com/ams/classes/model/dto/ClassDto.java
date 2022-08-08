package com.ams.classes.model.dto;

import java.util.List;

import com.ams.student.model.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString()
public class ClassDto {
    private int c_idx;          //클래스 idx
    private int t_idx;          //담당선생님 idx
    private String t_name;      //담당선생님 이름
    private String c_nm;        //클래스 명
    private String c_en_dt;     //클래스 등록일자
    private String c_cap;       //클래스 허용인원
    private String c_st_dt;     //클래스 시작일
    private String c_ed_dt;     //클래스 종료일
    private String c_st_tm;     //클래스 시작시간
    private String c_ed_tm;     //클래스 종료시간
    private String c_wkd;       //클래스 요일
    private String c_color;     //클래스 표시 컬러(달력)
    private String c_detail;    //클래스 상세정보
    private List<Integer> st_idx; // 클래스 등록 원생 리스트
    private List<StudentDto> st_list; // 클래스 등록 원생 리스트 (학생명)
    private int current_student_count;
    //조회시 필요한 변수(클래스 등록시)
    private String age_min;        //나이 min
    private String age_max;        //나이 max
    private String st_nm;       //학생 이름
    private String st_schl;     //학생 학교
}
