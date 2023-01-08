package com.ams.account.controller.api;

import com.ams.account.model.dto.AccountDto;
import com.ams.account.service.AccountService;
import com.ams.common.model.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountApiController {

    @Autowired
    private AccountService service;

    @PostMapping("/account/description")
    public ResponseEntity<?> dscrRegister(@RequestBody AccountDto dto){
        String msg= "";
        String data = "";

        //클래스 먼저 생성
        int result = service.insertDescription(dto);
        if(result==0){
            msg = "적요 등록 실패";
            return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.BAD_REQUEST);
        }else{
            }
            msg ="적요 등록 완료";
            return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.OK);
        }

        @GetMapping("/account/description")
        public ResponseEntity<?> getDscr() {
            int result=1;
            String msg= "";
            List<AccountDto> data = service.selectDescription();
            return new ResponseEntity<>(new ResponseDto<List<AccountDto>>(result,msg,data), HttpStatus.OK);
        }

        @DeleteMapping("/account/description/{dscr_idx}")
        public ResponseEntity<?> dscrDelete(@PathVariable int dscr_idx){
            int result=1;
            String msg= "";
            String data = "";
            result = service.deleteDescription(dscr_idx);
            return new ResponseEntity<>(new ResponseDto<String>(result,msg,data), HttpStatus.OK);
        }
    }
