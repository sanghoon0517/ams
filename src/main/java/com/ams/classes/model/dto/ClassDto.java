package com.ams.classes.model.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClassDto {
    private int c_idx;
    private int t_idx;
    private String c_nm;
    private String c_en_dt;
    private String c_cap;
    private String c_st_dt;
    private String c_ed_dt;
    private String c_st_tm;
    private String c_ed_tm;
    private String c_wkd;
    private String c_color;
    private String c_detail;
    private List<Integer> st_idx; // 클래스 등록 원생 리스트
    private int current_student_count;;
}
