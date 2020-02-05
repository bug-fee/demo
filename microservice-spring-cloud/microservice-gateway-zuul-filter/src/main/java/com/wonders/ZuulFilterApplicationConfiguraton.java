package com.wonders;

import com.wonders.filter.PreZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
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
public class ZuulFilterApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(ZuulFilterApplicationConfiguraton.class,args);
    }


    @Bean
    public PreZuulFilter preZuulFilter() {
        return new PreZuulFilter();
    }
}

