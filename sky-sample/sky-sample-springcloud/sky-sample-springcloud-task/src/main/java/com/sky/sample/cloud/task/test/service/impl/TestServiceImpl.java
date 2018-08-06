package com.sky.sample.cloud.task.test.service.impl;

import com.sky.sample.cloud.order.entity.OrderEntity;
import com.sky.sample.cloud.task.test.dao.TestDao;
import com.sky.sample.cloud.task.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<OrderEntity> orderList() {
        Map<String, Object> map = new HashMap<>();
        return testDao.getOrders(map);
    }
}
