package com.ams.account.service;

import com.ams.account.model.dto.AccountDto;
import com.ams.classes.model.dto.ClassDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    public int insertDescription(AccountDto dto);
    public List<AccountDto> selectDescription();
    public int deleteDescription(int dscr_idx);
}
