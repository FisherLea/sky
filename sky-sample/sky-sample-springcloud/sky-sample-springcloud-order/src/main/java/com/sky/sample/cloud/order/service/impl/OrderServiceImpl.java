package com.sky.sample.cloud.order.service.impl;

import com.sky.sample.cloud.order.dao.OrderDao;
import com.sky.sample.cloud.order.entity.OrderEntity;
import com.sky.sample.cloud.order.remoting.AccountRemoting;
import com.sky.sample.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountRemoting accountRemoting;

    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Override
    public void saveOrder() {
        System.out.println("saving order info");
        accountRemoting.updateAccount();

        System.out.println("finish order info");
    }

    @Override
    public List<OrderEntity> getOrderList() {
        Map<String, Object> map = new HashMap<>();
        return orderDao.getOrders(map);
    }

    @Transactional
    @Override
    public void insertTest(OrderEntity entity) {
        orderDao.insert(entity);
        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int x = 1/0;
    }


}
