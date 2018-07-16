package com.github.sky.spring.mvc.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共的Entity基类
 * Created by viruser on 2018/7/16.
 */
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -5336643738633555920L;

    protected Long id;
    protected Date ctime;
    protected Date mtime;
    protected Integer isvalid;
    protected String remark;
    protected Long creator;
    protected Long updator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }
}
