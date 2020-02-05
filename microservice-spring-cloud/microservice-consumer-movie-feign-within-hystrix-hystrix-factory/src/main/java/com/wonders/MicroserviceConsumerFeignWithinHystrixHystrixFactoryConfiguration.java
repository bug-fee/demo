package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

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
public class MicroserviceConsumerFeignWithinHystrixHystrixFactoryConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerFeignWithinHystrixHystrixFactoryConfiguration.class, args);
    }
}
