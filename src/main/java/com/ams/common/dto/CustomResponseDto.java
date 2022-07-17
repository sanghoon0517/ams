package com.ams.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseDto<T> {
	private int code; //1은 성공, -1은 실패
	private String message;
	private T data;
}
