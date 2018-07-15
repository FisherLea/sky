package com.sky.sample.cloud.inventory.controller;

import com.sky.sample.cloud.inventory.common.config.DynamicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private DynamicConfig dynamicConfig;

    @RequestMapping("/test")
    public String test(){
        return dynamicConfig.getSkyTestName();
    }
}
