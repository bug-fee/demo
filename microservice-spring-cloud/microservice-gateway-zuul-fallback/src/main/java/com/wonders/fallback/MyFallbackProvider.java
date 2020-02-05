package com.wonders.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 配置需要使用回退机制的路由
 *
 * @author Wang si rui
 * @version 1.0
 * @return 返回值
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 1、 <br>
 */
@Component
public class MyFallbackProvider implements ZuulFallbackProvider {
    @Override
    //返回指定路由的名称或微服务的服务ID可以定制回退，希望为所有路由提供默认回退时可以返回*或null。
    public String getRoute() {
        return "microservice-provider-user";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //设置http请求的响应码为400 和 bad request
                //此处为枚举类型，与其他被重写的方法保持一致即可
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //设置返回的状态码为400
                return HttpStatus.BAD_REQUEST.value();
            }

            @Override
            public String getStatusText() throws IOException {
                //返回的错误描述：定义默认为“Bad Request”
                return HttpStatus.BAD_REQUEST.getReasonPhrase();
            }

            /**
             *  浏览器关闭此请求时，自定义需要释放的资源
             * @author Wang si rui
             */
            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                //断路器打开时返回“fallback”字符串到页面
                return new ByteArrayInputStream("fallback".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //设置返回内容为json字符串
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}