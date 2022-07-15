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
	
	@Override
	public String toString() {
		String dtoprint;
		dtoprint = "[TeacherDto] : " +
		"t_idx : "+t_idx+
		"t_id : "+t_id+
		"t_pwd : "+t_pwd+
		"t_pn : "+t_pn+
		"t_role : "+t_role;
		return dtoprint;
	}
	
}
