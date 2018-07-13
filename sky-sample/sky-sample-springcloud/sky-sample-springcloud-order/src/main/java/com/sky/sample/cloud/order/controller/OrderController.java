package com.sky.sample.cloud.order.controller;

import com.sky.sample.cloud.order.remoting.AccountRemoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viruser on 2018/7/13.
 */
@RestController
public class OrderController {

    @Autowired
    private AccountRemoting accountRemoting;

    @RequestMapping("/orderList")
    public String orderList(String tag){
        String result = accountRemoting.accountList(tag);
        System.out.println(result);
        return result;
    }
}
