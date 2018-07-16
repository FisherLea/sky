package com.github.sky.spring.mvc.base.dao;

import java.util.List;

/**
 * 公共dao接口基类
 * Created by viruser on 2018/7/16.
 */
public interface BaseDao {

    /**
     * 新增一个实体信息
     * @param entity
     * @param <T>
     * @return
     */
    <T> long insert(T entity);

    /**
     * 批量新增实体信息
     * @param list
     * @param <T>
     * @return
     */
    <T> long insert(List<T> list);

    /**
     * 根据id删除一条记录
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 删除一个entity
     * @param paramObj
     * @param <T>
     * @return
     */
    <T> int delete(Object paramObj);

    /**
     * 修改一个实体信息
     * @param entity
     * @param <T>
     * @return
     */
    <T> int update(T entity);

    /**
     * 批量更新数据
     * @param list
     * @param <T>
     * @return
     */
    <T> int update(List<T> list);

    /**
     * 根据id查询
     * @param id
     * @param <T>
     * @return
     */
    <T> T getById(Long id);

    /**
     * 根据条件参数查询列表
     * @param paramObj
     * @param <T>
     * @return
     */
    <T> List<T> listBy(Object paramObj);
}
