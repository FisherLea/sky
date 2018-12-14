package com.sky.sample.cloud.order.service.impl;

import com.sky.sample.cloud.order.entity.OrderEntity;
import com.sky.sample.cloud.order.service.OrderService;
import com.sky.sample.cloud.order.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by viruser on 2018/8/8.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private OrderService orderService;

    @Override
    public void saveTest(OrderEntity entity) {
        orderService.insertTest(entity);
    }
}
