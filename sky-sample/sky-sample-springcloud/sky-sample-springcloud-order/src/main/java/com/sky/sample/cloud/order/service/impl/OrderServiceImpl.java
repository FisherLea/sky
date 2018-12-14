package com.sky.sample.cloud.order.service.impl;

import com.sky.sample.cloud.order.dao.OrderDao;
import com.sky.sample.cloud.order.entity.OrderEntity;
import com.sky.sample.cloud.order.remoting.AccountRemoting;
import com.sky.sample.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountRemoting accountRemoting;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

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

       // testTran();

        transactionTemplate.execute(new TransactionCallback<OrderEntity>() {
            @Override
            public OrderEntity doInTransaction(TransactionStatus status) {
                return null;
            }
        });

        //测试在注解事务方法中进行远程调用
        //经过测试，此时上面一句代码已经将数据插入数据库中并进行提交了，在这个事务方法中有远程调用，但是
        //远程服务调用中出现了异常，那事务是不会回滚的
        System.out.println(accountRemoting.accountList("Test"));
    }

    public void testTran(){
        System.out.println("test transaction.");
        int x = 1 / 0;
    }

}
