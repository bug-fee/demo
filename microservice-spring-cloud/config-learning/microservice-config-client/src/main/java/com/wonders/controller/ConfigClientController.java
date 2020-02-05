package com.wonders.controller;

import org.springframework.beans.factory.annotation.Value;
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
public class ConfigClientController {

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
