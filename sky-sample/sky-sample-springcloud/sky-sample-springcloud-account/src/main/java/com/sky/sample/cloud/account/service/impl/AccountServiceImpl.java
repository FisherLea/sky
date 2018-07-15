package com.sky.sample.cloud.account.service.impl;

import com.sky.sample.cloud.account.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Transactional
    @Override
    public void update() {
        System.out.println("update account info.");
        int x = 1 / 0;
    }
}
