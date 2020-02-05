package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
//支持发现其他客户端
@EnableDiscoveryClient
public class ConfigClientWithinEurekaApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientWithinEurekaApplicationConfiguraton.class, args);
    }


}

