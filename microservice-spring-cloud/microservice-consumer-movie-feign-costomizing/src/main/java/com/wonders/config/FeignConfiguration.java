package com.wonders.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 10281030
 * @Rewrite record：
 * 1、
 */
//feignConfiguration 不能和@ComponentScan的类放在同一个包内否则会引起冲突,该类属于feign的子容器
@Configuration
public class FeignConfiguration {

    //使用feign提供的契约，不采用Spring feign
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }

}
