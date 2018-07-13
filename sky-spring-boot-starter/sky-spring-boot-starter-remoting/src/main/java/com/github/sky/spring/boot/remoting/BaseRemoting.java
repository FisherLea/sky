package com.github.sky.spring.boot.remoting;

import com.github.sky.commons.httpclient.HttpClientUtils;
import com.github.sky.spring.boot.remoting.ribbon.CustomRequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.util.Map;

/**
 * 封装公共的远程调用方法
 * Created by sky on 2018/7/13.
 */
public class BaseRemoting {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public <T> T postForObject(String serviceId, String path, Map<String, String> params, Class<T> clazz) {
        String result = post(serviceId, path, params);
        return null;
    }

    public String post(String serviceId, String path, Map<String, String> params) {
        //根据服务id去解析出来对应的访问地址
        CustomRequestContext requestContext = resolveUri(serviceId, path);
        if (requestContext != null && requestContext.getMetadata() != null) {
            //解析头信息
        }
        return httpClientUtils.post(requestContext.getUrl(), params);
    }

    private CustomRequestContext resolveUri(String serviceId, String path) {
        if (StringUtils.isBlank(serviceId)) {
            throw new RuntimeException("服务id为空");
        }
        String domain = null;
        CustomRequestContext requestContext = new CustomRequestContext();
        if (serviceId.startsWith("http")) {
            domain = serviceId;
        } else {
            ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
            if (serviceInstance == null) {
                throw new RuntimeException("未找到" + serviceId + "对应的服务注册实例");
            }
            Map<String, String> metadata = serviceInstance.getMetadata();
            if (metadata != null && metadata.get("context.path") != null) {
                //如果.properties配置文件中配置了context.path属性，那么还需要加上上下文路径
                path = metadata.get("context.path") + path;
            }
            //字符串格式为 http://主机名:端口号 格式
            domain = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
            requestContext.setMetadata(metadata);
        }
        String url = null;
        if (path == null) {
            path = "";
        }
        if (path.startsWith("/")) {
            url = StringUtils.join(domain, path);
        } else {
            //如果path不是/开始的，需要在中间加上/
            url = StringUtils.join(domain, "/", path);
        }
        requestContext.setUrl(url);
        return requestContext;
    }

}
