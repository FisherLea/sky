package com.sky.sample.cloud.order.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by viruser on 2018/7/13.
 */
@RefreshScope
@Configuration
public class DynamicConfig {

    @Value("${sky.account.url}")
    private String skyAccountServiceId;

    @Value("${sky.inventory.url}")
    private String skyInventoryServiceId;

    @Value("${sky.test.name}")
    private String skyTestName;

    public String getSkyInventoryServiceId() {
        return skyInventoryServiceId;
    }

    public String getSkyAccountServiceId() {
        return skyAccountServiceId;
    }

    public String getSkyTestName() {
        return skyTestName;
    }
}
