package com.sky.sample.cloud.task.test.dao;

import com.sky.sample.cloud.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */
public interface TestDao {

    List<OrderEntity> getOrders(Map<String, Object> map);
}
