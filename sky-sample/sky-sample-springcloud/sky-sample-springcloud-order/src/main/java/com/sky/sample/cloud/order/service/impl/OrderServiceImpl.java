package com.sky.sample.cloud.order.service.impl;

import com.sky.sample.cloud.order.remoting.AccountRemoting;
import com.sky.sample.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountRemoting accountRemoting;

    @Transactional
    @Override
    public void saveOrder() {
        System.out.println("saving order info");
        accountRemoting.updateAccount();

        System.out.println("finish order info");
    }
}
