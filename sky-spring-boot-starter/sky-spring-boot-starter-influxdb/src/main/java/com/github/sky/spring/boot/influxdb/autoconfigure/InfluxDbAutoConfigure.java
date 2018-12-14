package com.github.sky.spring.boot.influxdb.autoconfigure;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({InfluxDbProperties.class})
public class InfluxDbAutoConfigure {

    @Autowired
    private InfluxDbProperties influxDbProperties;

    @Bean
    @ConditionalOnMissingBean
    public InfluxDB influxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxDbProperties.getUrl(),
                influxDbProperties.getUsername(), influxDbProperties.getPassword());
        return influxDB;
    }
}

