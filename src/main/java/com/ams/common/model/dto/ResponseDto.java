package com.ams.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
	int code; // 성공여부 반환 : 성공시 1 실패시 0
	String msg; // 메세지 반환
	T data; // 넘기는 데이터값 반환
}