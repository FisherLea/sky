package com.github.sky.spring.boot.remoting.autoconfigure;

import com.github.sky.commons.httpclient.HttpClientUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by viruser on 2018/7/13.
 */
@Configuration
//条件注解，表示在HttpClientUtils这个类满足条件时怎么.....
@ConditionalOnClass({HttpClientUtils.class})
// 该注解的作用是将属性配置文件中的属性信息封装到XxxProperties类对象中，然后
// 会将其注入到spring容器里，方便后续使用
@EnableConfigurationProperties()
public class HttpRemotingAutoConfigure {

    //这个starter的作用就是把HttpClientUtils这个bean给注入到spring容器中，给项目中其他模块使用
    @Bean
    @ConditionalOnMissingBean
    public HttpClientUtils httpClientUtils(){
        //这里其实很简单，就是new一个对象然后返回，这样spring boot自动配置机制就会默认以
        // 这个加了@Bean注解的方法名作为beanName将其注入到spring容器中
        HttpClientUtils clientUtils = new HttpClientUtils();
        clientUtils.init();//执行初始化操作
        return clientUtils;
    }

    //写好了这个AutoConfigure之后，只需要在classpath下面新建一个META-INF目录，再在里面新建一个spring.factories
    //文件，然后将这个类配置好即可，这样spring boot启动时会到jar包中去找这个文件，从而实现自动配置
}
