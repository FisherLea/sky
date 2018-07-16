package com.sky.sample.cloud.order.dao.impl;

import com.sky.sample.cloud.order.common.dao.OrderBaseDaoImpl;
import com.sky.sample.cloud.order.dao.OrderDao;
import com.sky.sample.cloud.order.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/7/16.
 */
@Repository
public class OrderDaoImpl extends OrderBaseDaoImpl implements OrderDao {


    @Override
    public List<OrderEntity> getOrders(Map<String, Object> map) {
        return super.listBy(map);
    }
}
