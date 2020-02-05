package com.wonders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

//SpringBootApplication需要在group id 包下最外层，该组合注解包含@ComponentScan扫描注解
@SpringBootApplication()
//支持客户端发现：（Eureka专用注解,当前服务只能被eureka发现不能发现其他客户端，如果需要发现其他客户端可以使用@EnabledDiscoveryClient）
@EnableEurekaClient
//支持发现其他客户端@EnableDiscoveryClient
//@EnableDiscoveryClient
public class MicroserviceFileUploadConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceFileUploadConfiguration.class, args);
    }

    @Value("${spring.server.MaxFileSize}")
    private String MaxFileSize;
    @Value("${spring.server.MaxRequestSize}")
    private String MaxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(MaxFileSize); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(MaxRequestSize);
        return factory.createMultipartConfig();
    }
}
