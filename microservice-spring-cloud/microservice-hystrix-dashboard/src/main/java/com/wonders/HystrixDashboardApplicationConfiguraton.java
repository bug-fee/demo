package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

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
//仅仅开启hystrixDDashboard支持,不开启 hystrix-metrics-event-stream等依赖。在访问 http://localhost:8030/hystrix地址后，输入其他hystrix服务地址也可以使用面板监听其他服务
@EnableHystrixDashboard
public class HystrixDashboardApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplicationConfiguraton.class, args);
    }
}

