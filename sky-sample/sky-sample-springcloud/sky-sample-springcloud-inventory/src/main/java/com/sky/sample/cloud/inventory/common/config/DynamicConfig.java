package com.sky.sample.cloud.inventory.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
public class DynamicConfig {

    @Value("${sky.test.name}")
    private String skyTestName;

    public String getSkyTestName() {
        return skyTestName;
    }
}
