package com.wonders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

/**
 * config-client 启动顺序：
 * 先加载bootstrap.*里面的配置，加载完之后链接config server 加载远程配置信息，最后加载application.*里面的配置
 */
@SpringBootApplication
//支持服务发现
public class ConfigClientApplicationConfiguraton {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplicationConfiguraton.class, args);
    }


}

