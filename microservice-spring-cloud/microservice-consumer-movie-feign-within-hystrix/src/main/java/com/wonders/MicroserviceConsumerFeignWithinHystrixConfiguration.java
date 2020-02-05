package com.wonders;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * 使用feign代替restTemplate
 *
 * @author Wang si rui
 * @version 1.0
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 1、 <br>
 */
@SpringBootApplication
@EnableEurekaClient
//开启feign支持
@EnableFeignClients
//支持断路器
@EnableCircuitBreaker
//支持断路器控制面板http://localhost:7906/hystrix
@EnableHystrixDashboard
public class MicroserviceConsumerFeignWithinHystrixConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerFeignWithinHystrixConfiguration.class, args);
    }

    /**
     * 使用 http://localhost:7906/hystrix.stream
     * @author Wang si rui
     * @version 1.0
     */
    @Bean
    public ServletRegistrationBean getServlet(){

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        //系统启动时加载顺序
        registrationBean.setLoadOnStartup(1);
        //设置hystrix 输出流路径，通过http://localhost:7906/hystrix 进入控制面板路径录入输出流路径可在面板查看hystrix状态
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
