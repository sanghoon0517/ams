package com.ams.account.model.dao;

import com.ams.account.model.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
    public int insertDescription(AccountDto vo);
    public List<AccountDto> selectDescription();
    public int deleteDescription(int dscr_idx);
}
