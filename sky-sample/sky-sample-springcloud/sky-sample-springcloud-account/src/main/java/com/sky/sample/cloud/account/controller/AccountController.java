package com.sky.sample.cloud.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viruser on 2018/7/13.
 */
@RestController
//@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/accountList")
    public String accountList(String tag){

        return "Jack, Lily" + tag;
    }
}
