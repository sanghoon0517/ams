package com.ams.teacher.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TeacherDto {
	
	private int t_idx;
	
	@NotEmpty(message = "id는 공백 불가")
	@NotNull(message = "id는 null 불가")
	@Size(min=3, max=15, message = "3~20자 제한")
	private String t_id;
	@NotEmpty(message = "pwd는 공백 불가")
	@NotNull(message = "pwd는 null 불가")
	private String t_pwd;
	private String t_pn;
	private Role t_role;
	private String t_name;
	@NotEmpty(message = "t_email 공백 불가")
	@NotNull(message = "t_email null 불가")
	private String t_email;
}
