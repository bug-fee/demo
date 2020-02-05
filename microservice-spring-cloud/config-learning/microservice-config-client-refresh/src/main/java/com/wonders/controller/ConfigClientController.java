package com.wonders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
@RestController

// spring-boot-starter-actuator该依赖增加了/refresh 路径（必须是POST请求）,访问/refresh 路径时能做到去刷新含有@RefreshScope注解的springBean，远端配置文件更改后，configClient可以达到刷新配置信息的目的
// 当有业务没有走完时将保留原有的配置信息暂时不做变更直到该业务完成，新发送过来的请求将采用新的配置信息去执行。
//测试:命令行运行 CURL -X POST http://127.0.0.1:8081/refresh
@RefreshScope//不建议@refreshScope和@Configuration配置主类在一起使用
public class ConfigClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigClientController.class);

    @Autowired
    RefreshEndpoint refreshEndpoint;

    /*
     * 此处的profile是从configServer中获取的配置信息，当class加载路径下没有bootstrap.yml文件时(空文件也可)项目将启动失败。
     * 因为config-client 先加载bootstrap.*里面的配置，加载完之后链接config server 加载远程配置信息，最后加载application.*里面的配置
     */
    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String getProfile() {
        return this.profile;
    }

}
