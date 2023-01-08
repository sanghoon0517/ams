package com.ams.account.controller;


import com.ams.account.model.dto.AccountDto;
import com.ams.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService service;
    @GetMapping("/account")
    public String accountList(Model model){

        return "account/list";
    }

}
