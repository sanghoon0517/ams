package com.ams.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ams.attendance.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AttendanceController {
	
	@GetMapping("/attendance")
	public String getAttendancePage() {
		
		return "attendance/attendance-manage";
	}
}
