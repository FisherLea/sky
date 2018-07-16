package com.sky.sample.cloud.order.service;

import com.sky.sample.cloud.order.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    void saveOrder();

    List<OrderEntity> getOrderList();
}
