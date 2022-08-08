package com.ams.schedule.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.schedule.model.dao.ScheduleDao;
import com.ams.schedule.model.dto.ScheduleDto;

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
    public List<ScheduleDto> getSchedule() {
        List<ScheduleDto> list= dao.getSchedule();
        return list;
    }
    
}
