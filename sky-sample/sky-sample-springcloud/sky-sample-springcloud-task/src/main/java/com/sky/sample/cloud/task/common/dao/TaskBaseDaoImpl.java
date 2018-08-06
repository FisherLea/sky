package com.sky.sample.cloud.task.common.dao;

import com.github.sky.spring.mvc.base.dao.BaseDao;
import com.github.sky.spring.mvc.base.dao.BaseDaoImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by viruser on 2018/8/6.
 */
public class TaskBaseDaoImpl extends BaseDaoImpl implements BaseDao {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSession(sqlSessionTemplate);
        //super.setReadSessionTemplate(sqlSessionTemplate);
        //super.setWriteSessionTemplate(sqlSessionTemplate);
    }
}
