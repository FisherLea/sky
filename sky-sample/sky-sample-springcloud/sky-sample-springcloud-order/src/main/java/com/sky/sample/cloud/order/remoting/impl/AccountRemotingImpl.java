package com.sky.sample.cloud.order.remoting.impl;

import com.github.sky.spring.boot.remoting.BaseRemoting;
import com.sky.sample.cloud.order.common.config.DynamicConfig;
import com.sky.sample.cloud.order.remoting.AccountRemoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by viruser on 2018/7/13.
 */
@Component
public class AccountRemotingImpl extends BaseRemoting implements AccountRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    public String accountList(String tag) {
        Map<String, String> param = new HashMap<>();
        param.put("tag", tag);
        return post(dynamicConfig.getSkyAccountServiceId(), "/accountList", param);
    }
}
