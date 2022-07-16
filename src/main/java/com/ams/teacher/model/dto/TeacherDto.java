package com.ams.teacher.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TeacherDto {
	private int t_idx;
	private String t_id;
	private String t_pwd;
	private String t_pn;
	private Role t_role;
	private String t_name;
}
