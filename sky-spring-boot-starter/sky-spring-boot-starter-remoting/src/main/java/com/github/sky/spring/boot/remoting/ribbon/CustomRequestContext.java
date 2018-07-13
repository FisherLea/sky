package com.github.sky.spring.boot.remoting.ribbon;

import java.util.Map;

/**
 * 自定义请求上下文信息
 * Created by yjs on 2018/7/13.
 */
public class CustomRequestContext {

    private String url;

    private Map<String, String> metadata;

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
