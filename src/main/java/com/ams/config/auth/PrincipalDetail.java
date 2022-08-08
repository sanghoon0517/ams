package com.ams.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ams.teacher.model.dto.TeacherDto;

public class PrincipalDetail implements UserDetails{

    private TeacherDto teacher;

    
    public PrincipalDetail(TeacherDto teacher) {
		this.teacher=teacher;
	}
    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return "ROLE_"+teacher.getT_role(); // ROLE_USER // 앞에 ROLE_ 을 꼭 붙여야 인식함
			}
		});
		return collectors;
	}

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return teacher.getT_pwd();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return teacher.getT_id();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


}
