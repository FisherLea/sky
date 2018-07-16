package com.sky.sample.cloud.order.dao;

import com.sky.sample.cloud.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/7/16.
 */
public interface OrderDao {

    List<OrderEntity> getOrders(Map<String, Object> map);
}
