package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 2018-10-20
 * @Rewrite record：
 * 1、
 */
@SpringBootApplication
//支持zuul代理
@EnableZuulProxy
public class ZuulRegExpApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(ZuulRegExpApplicationConfiguraton.class, args);
    }

    /**
     * 使用正则表达式反向代理指定的服务群
     * PS ：被代理的服务 spring.application.name和eureka.instance.appname必须一致且后缀添加 "-v*"(服务启动后代理路径可能延迟生效)
     * 访问路径为   http://127.0.0.1:8040/v1/microservice-provider-user/simple/1
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        //入参一:被代理的服务名称正则表达式
        //入参二:路由地址为 /version/name
        //如果不配置PatternServiceRouteMapper将默认使用缺省路径（zuul.routes.path=*）进行反向代理,如果同时配置同时生效
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }

}

