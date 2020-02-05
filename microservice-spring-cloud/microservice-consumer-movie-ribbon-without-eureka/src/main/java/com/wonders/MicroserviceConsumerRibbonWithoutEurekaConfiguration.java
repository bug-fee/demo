package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceConsumerRibbonWithoutEurekaConfiguration {

    public static void main(String[] args) {
        //日期类型格式化显示为yyyyMMdd
        //Date now = new Date();
        //System.out.println(String.format("%tC%ty%tm%td", now, now, now, now));
//        System.out.printf("日期类型格式化显示为yyyyMMdd：%tC%ty%tm%td",now);

        //启动springboot 容器
        SpringApplication.run(MicroserviceConsumerRibbonWithoutEurekaConfiguration.class, args);

    }

    @Bean
    //添加LoadBalanced就能让RestTemplate在请求时拥有客户端负载均衡能力
    @LoadBalanced
    //初始化bean：方法名称代表注入的bean的id;如果是按类型匹配则bean的ID可以与方法名称不一致（测试可行）
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
