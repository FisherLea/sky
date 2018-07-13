package com.github.sky.commons.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用httpclient进行通讯的工具类
 * Created by sky on 2018/7/12.
 */
@Slf4j
public class HttpClientUtils {

    /**
     * 连接池最大连接数
     */
    private static final int MAX_TOTAL_POOL = 600;

    /**
     * 每个路由的最大连接数
     */
    private static final int MAX_PER_ROUTE_POOL = 300;

    /**
     * socket连接请求超时时间 默认30秒
     */
    private static final int SOCKET_TIME_OUT = 30000;

    /**
     * 连接超时时间 30秒
     */
    private static final int CONNECT_TIME_OUT = 30000;

    private HttpClientBuilder builder;

    private CloseableHttpClient httpClient;

    public void init() {

        SSLContext sslContext = null;
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        //初始化连接池
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(MAX_TOTAL_POOL);
        manager.setDefaultMaxPerRoute(MAX_PER_ROUTE_POOL);

        //自定制需要的配置
        this.builder = HttpClients.custom().setConnectionManager(manager).setSSLSocketFactory(sslFactory);
        this.httpClient = builder.build();
    }

    /**
     * 封装post请求
     *
     * @param uri
     * @param params
     * @return
     */
    public String post(String uri, Map<String, String> params) {
        return post(uri, null, params);
    }

    /**
     * 封装post请求可以设置头信息
     *
     * @param uri
     * @param headers
     * @param params
     * @return
     */
    public String post(String uri, Map<String, String> headers, Map<String, String> params) {
        return post(uri, headers, params, "UTF-8", "UTF-8");
    }

    /**
     * 封装post请求，可以设置请求头信息，请求体编码字符集和响应体编码字符集
     *
     * @param uri
     * @param headers
     * @param params
     * @param requestCharset
     * @param responseCharset
     * @return
     */
    public String post(String uri, Map<String, String> headers, Map<String, String> params,
                       String requestCharset, String responseCharset) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        //发起一个post请求，使用HttpPost对象完成
        HttpPost httpPost = new HttpPost(uri);

        //转换头信息和传输的参数信息
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        //使用BasicNameValuePair基于键值对的类进行传输参数的转换封装
        List<BasicNameValuePair> nvps = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
            }
        }

        //需要为HttpPost实体设置一个HttpEntity，其中包含了封装的请求参数和编码字符集信息
        try {
            client = httpClient;
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, requestCharset));
            HttpEntity entity = execute(client, response, httpPost);
            if (entity != null) {
                //得到响应的HttpEntity后，可以通过EntityUtils工具类将其转成字符串，官方不推荐这种方式
                //除非响应实体从一个可信HTTP服务器发起和已知是有限长度的
                String result = EntityUtils.toString(entity);
                log.info("response result: " + result);
                return result;
                //方式二：可以使用InputStream流进行处理
            }
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的字符集编码" + requestCharset, e);
        } catch (IOException e) {
            log.error("请求响应体内容转换字符串失败", e);
        } finally {
            //记得释放资源
            close(response);
        }
        return null;
    }

    private void close(CloseableHttpResponse response) {
        if(response != null){
            try {
                response.close();
                response = null;
            } catch (IOException e) {
                log.error("关闭response流失败", e);
            }
        }
    }

    /**
     * 执行请求
     *
     * @param client
     * @param response
     * @param requestBase
     * @return
     */
    private HttpEntity execute(CloseableHttpClient client, CloseableHttpResponse response, HttpRequestBase requestBase) {
        //设置请求超时的时间
        setTimeout(requestBase);
        try {
            response = client.execute(requestBase);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return response.getEntity();
            } else {
                log.error(requestBase.getURI() + " 请求错误，响应状态码: " + statusCode);
            }
        } catch (IOException e) {
            log.error(requestBase.getURI() + "请求出错!", e);
        }
        return null;
    }

    /**
     * 设置连接超时时间
     *
     * @param requestBase
     */
    private void setTimeout(HttpRequestBase requestBase) {
        RequestConfig config = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
        //将自定制的requestConfig设置给requestBase对象
        requestBase.setConfig(config);
    }
}
