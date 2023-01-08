package com.ams.account.service;

import com.ams.account.model.dao.AccountDao;
import com.ams.account.model.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao dao;

    @Override
    public int insertDescription(AccountDto dto) {
        int result=0;
        result = dao.insertDescription(dto);
        return result;
    }

    @Override
    public List<AccountDto> selectDescription() {
        List<AccountDto> result = dao.selectDescription();
        return result;
    }

    @Override
    public int deleteDescription(int dscr_idx) {
        int result = 0;
        result = dao.deleteDescription(dscr_idx);
        return result;
    }
}
