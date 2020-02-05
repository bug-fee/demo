package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
//SpringBootApplication需要在group id 包下最外层，该组合注解包含@ComponentScan扫描注解
public class MicroserviceProviderConfiguration {

    public static void main(String[] args) { SpringApplication.run(MicroserviceProviderConfiguration.class, args); }
}
