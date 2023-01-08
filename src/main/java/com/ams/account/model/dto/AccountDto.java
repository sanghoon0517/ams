package com.ams.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString()
public class AccountDto {
    private String dscr_type;
    private String dscr_detail;
    private int dscr_idx;
}
