package com.ams.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ams.teacher.model.dto.TeacherDto;
import com.ams.teacher.service.TeacherSerivce;


@Service
public class PrincipleDetailService implements UserDetailsService{

    @Autowired
    TeacherSerivce teacherSerivce;

    @Override
    public UserDetails loadUserByUsername(String t_id) throws UsernameNotFoundException {
        TeacherDto principal = teacherSerivce.selectOneDao(t_id);


        return new PrincipalDetail(principal);
    }
    
}
