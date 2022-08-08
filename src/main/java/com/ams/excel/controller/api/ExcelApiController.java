package com.ams.excel.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelApiController {
	
	@GetMapping()
	public ResponseEntity<?> exportStudentListExcel() {
		
		return null;
	}
}
