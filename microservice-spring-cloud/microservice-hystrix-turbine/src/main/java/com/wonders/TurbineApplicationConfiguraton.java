package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

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
//springboot上下文配置基类
@SpringBootApplication
//添加turbine支持
@EnableTurbine
public class TurbineApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplicationConfiguraton.class, args);
    }
}

