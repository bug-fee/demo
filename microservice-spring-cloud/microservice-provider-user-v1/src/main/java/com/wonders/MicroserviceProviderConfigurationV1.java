package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//SpringBootApplication需要在group id 包下最外层，该组合注解包含@ComponentScan扫描注解
@SpringBootApplication()
//支持客户端发现：（Eureka专用注解,当前服务只能被eureka发现不能发现其他客户端，如果需要发现其他客户端可以使用@EnabledDiscoveryClient）
@EnableEurekaClient
//支持发现其他客户端@EnableDiscoveryClient
//@EnableDiscoveryClient
public class MicroserviceProviderConfigurationV1 {

    public static void main(String[] args) { SpringApplication.run(MicroserviceProviderConfigurationV1.class, args); }
}
