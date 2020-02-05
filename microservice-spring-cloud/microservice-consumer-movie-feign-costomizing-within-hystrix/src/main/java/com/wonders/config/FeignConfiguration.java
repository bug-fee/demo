package com.wonders.config;

import feign.Contract;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * 对单个feignClient禁用hystrix支持
     *
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        //feignClient 的默认是 HystrixFeign.builder()此方法返回一个集成了Hystrix的Feign构建对象，我们修改成Feign.builder()可以去掉对Hystrix的支持（对单个feign禁用hystrix）
        //如果配置了Feign的回调函数或回调类无法执行回调时可以改回HystrixFeign.builder()；
        return HystrixFeign.builder();
    }

}
