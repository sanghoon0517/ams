package com.ams.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.schedule.model.dao.ScheduleDao;
import com.ams.schedule.model.dto.ScheduleDto;
import com.ams.schedule.model.dto.ScheduleParamDto;
import com.ams.student.model.dto.StudentDto;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDao dao;

    @Override
    public int registerSchedule(ScheduleDto dto) {
        // groupID
        UUID uuidTemp = UUID.randomUUID();
        String groupId = uuidTemp.toString();
        dto.setGroupId(groupId);
        int result = dao.insertRepeatSchedule(dto);
        return result;
    }


    @Override
    public List<ScheduleDto> getSchedule(ScheduleParamDto dto) {
        List<ScheduleDto> list= dao.getSchedule(dto);
        return list;
    }


    @Override
    public ScheduleDto getOneSchedule(int s_idx) {
        ScheduleDto dto = dao.getOneSchedule(s_idx);
        return dto;
    }


    @Override
    public int updateSchedule(ScheduleDto dto) {
        int result = dao.updateSchedule(dto);
        return result;
    }


    @Override
    public int deleteSchedule(int s_idx) {
        int result = dao.deleteSchedule(s_idx);
        return result;
    }


    @Override
    public List<StudentDto> getStudentSchedule(List<String> st_idx) {
        List<StudentDto> dto = dao.getStudentSchedule(st_idx);
        return dto;
    }
    
}
