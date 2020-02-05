package com.wonders.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181003
 * @Rewrite record：
 * 1、
 */
//feignConfiguration 不能和@ComponentScan的类放在同一个包内否则会引起冲突,该类属于feign的子容器
@Configuration
public class FeignConfiguration2 {
    /**
     * 如果eureka有用户名认证的话，客户端连接eureka需增加拦截器提供用户名密码
     *
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password123");
    }

    //给feign配置日志等级
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
