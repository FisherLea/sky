package com.sky.sample.cloud.order.common.dao;

import com.github.sky.spring.mvc.base.dao.BaseDao;
import com.github.sky.spring.mvc.base.dao.BaseDaoImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by viruser on 2018/7/16.
 */
public class OrderBaseDaoImpl extends BaseDaoImpl implements BaseDao {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSession(sqlSessionTemplate);
        //super.setReadSessionTemplate(sqlSessionTemplate);
        //super.setWriteSessionTemplate(sqlSessionTemplate);
    }
}
