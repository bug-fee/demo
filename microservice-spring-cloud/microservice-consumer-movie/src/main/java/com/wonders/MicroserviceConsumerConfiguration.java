package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceConsumerConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerConfiguration.class, args);
    }

    @Bean
    //添加LoadBalanced就能让RestTemplate在请求时拥有客户端负载均衡的能力
    @LoadBalanced
    //初始化bean：方法名称代表注入的bean id;如果是按类型匹配则beanID可以与方法名称不一致（测试可行）
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
