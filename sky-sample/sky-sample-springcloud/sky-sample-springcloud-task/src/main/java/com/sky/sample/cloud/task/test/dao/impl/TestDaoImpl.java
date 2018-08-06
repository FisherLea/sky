package com.sky.sample.cloud.task.test.dao.impl;

import com.sky.sample.cloud.order.entity.OrderEntity;
import com.sky.sample.cloud.task.common.dao.TaskBaseDaoImpl;
import com.sky.sample.cloud.task.test.dao.TestDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */
@Repository
public class TestDaoImpl extends TaskBaseDaoImpl implements TestDao {

    @Override
    public List<OrderEntity> getOrders(Map<String, Object> map) {
        return super.listBy(map);
    }
}
