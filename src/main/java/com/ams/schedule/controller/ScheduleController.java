package com.ams.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    

    @GetMapping("/schedule")
    public String schedulePage(){
        return "schedule/schedule";
    }
}
