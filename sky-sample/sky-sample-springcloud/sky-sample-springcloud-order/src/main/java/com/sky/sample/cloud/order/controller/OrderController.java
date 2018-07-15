package com.sky.sample.cloud.order.controller;

import com.sky.sample.cloud.order.remoting.AccountRemoting;
import com.sky.sample.cloud.order.service.OrderService;
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

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orderList")
    public String orderList(String tag){
        String result = accountRemoting.accountList(tag);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/payOrder")
    public String payOrder(String orderNo){
        orderService.saveOrder();
        return "success";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/aaa")
    public String aaa(){
        return "aaa";
    }
}
