package com.github.sky.spring.boot.remoting;

import com.github.sky.commons.httpclient.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 封装公共的远程调用方法
 * Created by sky on 2018/7/13.
 */
public class BaseRemoting {

    @Autowired
    private HttpClientUtils httpClientUtils;

    public <T> T postForObject(String serviceId, String path, Map<String, String> params, Class<T> clazz){
        String result = post(serviceId, path, params);
        return null;
    }

    public String post(String serviceId, String path, Map<String, String> params){

        return httpClientUtils.post(serviceId, params);
    }
}
