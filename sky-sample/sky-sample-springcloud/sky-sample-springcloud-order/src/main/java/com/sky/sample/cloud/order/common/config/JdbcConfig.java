package com.sky.sample.cloud.order.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by viruser on 2018/7/16.
 */
@Configuration
@EnableTransactionManagement
public class JdbcConfig {

    @Autowired
    private Environment env;

    //定义一个数据源
    @Bean
    @ConfigurationProperties("spring.datasource.druid.main") //定义数据源连接信息属性的前缀
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build(env, "spring.datasource.druid.main");
    }

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        return factoryBean;
    }

    @Bean(name = "sqlSessionTemplate", destroyMethod = "close")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
        return sqlSessionTemplate;
    }
}
