package com.github.sky.spring.mvc.base.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by viruser on 2018/7/16.
 */
public abstract class BaseDaoImpl implements BaseDao {

    private SqlSession sqlSession;
    private SqlSession readSessionTemplate;
    private SqlSession writeSessionTemplate;

    private String getStatement(String stmtId){
        String name = this.getClass().getName();
        String statement = name + "." + stmtId;
        //返回 命名空间.statementId  约定mapper映射文件中的namespace为Dao实现类的完全类型名称
        return statement;
    }

    @Override
    public <T> long insert(T entity) {
        return sqlSession.insert(getStatement("insert"), entity);
    }

    @Override
    public <T> long insert(List<T> list) {
        if (list != null && list.size() > 0) {
            return sqlSession.insert(getStatement("batchInsert"), list);
        }
        return 0L;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public <T> int delete(Object paramObj) {
        return 0;
    }

    @Override
    public <T> int update(T entity) {
        return 0;
    }

    @Override
    public <T> int update(List<T> list) {
        return 0;
    }

    @Override
    public <T> T getById(Long id) {
        return sqlSession.selectOne(getStatement("getById"), id);
    }

    @Override
    public <T> List<T> listBy(Object paramObj) {
        return sqlSession.selectList(getStatement("listBy"), paramObj);
    }

    @Override
    public <T> List<T> listBy(String stmtId, Object paramObj) {
        return sqlSession.selectList(getStatement(stmtId), paramObj);
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SqlSession getReadSessionTemplate() {
        return readSessionTemplate;
    }

    public void setReadSessionTemplate(SqlSession readSessionTemplate) {
        this.readSessionTemplate = readSessionTemplate;
    }

    public SqlSession getWriteSessionTemplate() {
        return writeSessionTemplate;
    }

    public void setWriteSessionTemplate(SqlSession writeSessionTemplate) {
        this.writeSessionTemplate = writeSessionTemplate;
    }
}
