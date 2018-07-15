package com.sky.sample.cloud.account.controller;

import com.sky.sample.cloud.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viruser on 2018/7/13.
 */
@RestController
//@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/accountList")
    public String accountList(String tag){

        return "Jack, Lily" + tag;
    }

    @RequestMapping("/updateAccount")
    public String updateAccount(){
        accountService.update();
        return "success";
    }
}
