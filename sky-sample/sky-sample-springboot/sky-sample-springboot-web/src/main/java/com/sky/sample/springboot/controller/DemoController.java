package com.sky.sample.springboot.controller;

import com.github.sky.spring.boot.remoting.BaseRemoting;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by viruser on 2018/7/13.
 */
@RestController
public class DemoController extends BaseRemoting {

    @Autowired
    private InfluxDB influxDB;

    @RequestMapping("/test")
    public String testRemoting(){
        String url = "https://www.taobao.com";
        Map<String, String> map = new HashMap<>();
        map.put("username", "xxxx");
        String result = post(url, "", map);
        return result;
    }

    @RequestMapping("/testInfluxdb")
    public String testInfluxDb(){
        influxDB.setDatabase("mydb");
        influxDB.setRetentionPolicy("test_myrp");
        Map<String, String> tags = new HashMap<>();
        tags.put("host", "server01");
        Point point = Point.measurement("test")
                .tag(tags)
                .addField("value", 1.99)
                .build();
        influxDB.write(point);
        return "success";
    }
}
