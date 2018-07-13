package com.github.sky.spring.boot.remoting.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by viruser on 2018/7/13.
 */
//通过prefix属性指定配置key的前缀信息
@ConfigurationProperties(prefix = "sky.remoting")
public class HttpRemotingProperties {

    //待定
}
