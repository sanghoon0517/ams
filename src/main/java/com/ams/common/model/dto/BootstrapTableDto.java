package com.ams.common.model.dto;

import java.util.List;

import com.ams.student.model.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootstrapTableDto<T> {
	private int total;
	private int totalNotFiltered;
	private T rows;
}
