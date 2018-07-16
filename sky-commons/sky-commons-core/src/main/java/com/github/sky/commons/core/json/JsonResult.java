package com.github.sky.commons.core.json;

import java.io.Serializable;

/**
 * 封装返回json数据的实体类
 * Created by viruser on 2018/7/16.
 */
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -7815772308283158718L;

    private Integer code;
    private String msg;
    private Object data;

    public JsonResult() {
        this.code = 1;
        this.msg = "success";
        this.data = null;
    }

    public JsonResult(Object data) {
        this.code = 1;
        this.msg = "success";
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
