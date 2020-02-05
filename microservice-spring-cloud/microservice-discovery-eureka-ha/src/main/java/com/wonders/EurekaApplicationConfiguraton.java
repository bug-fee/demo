package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
//支持服务发现
@EnableEurekaServer
public class EurekaApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplicationConfiguraton.class,args);
    }

    //支持web认证,
    @EnableWebSecurity
    public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //关闭csrf认证
            http.csrf().disable();
            super.configure(http);
        }
    }
}

