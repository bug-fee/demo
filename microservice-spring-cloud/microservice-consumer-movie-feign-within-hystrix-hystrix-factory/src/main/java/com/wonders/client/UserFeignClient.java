package com.wonders.client;

import com.wonders.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181029
 * @Rewrite record：
 * 1、
 */
//name属性表示添加feign支持代替restTemplate,填写要访问的服务端应用名称；此处应从配置文件中读取:"${serverName}"
//fallback表示指定一个hystrix断路器的回调类，该类必须实现此接口
//fallbackFactory指定hystrix回调工厂，进而控制请求失败原因
//PS:fallbackFactory可以理解为fallback的增强版可以获取情况失败的原因。fallback和fallbackFactory不能同时使用，如果同时使用只会前者生效。
@FeignClient(name = "microservice-provider-user", /*fallback = HystrixClientFallback.class, */fallbackFactory = HystrixClientFactory.class)
public interface UserFeignClient {

    //要访问的服务端的接口地址
    @GetMapping(value = "/simple/{id}")
    public User findUserById(@PathVariable("id") Long id);


    /**
     * 写法一：写在此类文件内：设置一个实现了UserFeignClient接口的类用于执行hystrix的断路器回调。
     * 写法二：写在此类文件外：定义Java类public修饰实现UserFeignClient接口 fallback指向该类。必须使用注解UserFeignClient标注该类否则将报错
     * PS：如果报错没有对应的服务则需要把feign对hystrix的支持打开，在application.yml中增加 feign.hystrix.enabled: true
     *
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    static class HystrixClientFallback1 implements UserFeignClient {
        @Override
        public User findUserById(Long id) {
            User user = new User();
            user.setId(0L);
            user.setName("小刚");
            user.setAge((short) 1);
            user.setBalance(new BigDecimal(0));
            user.setUsername("xiaogang");
            return user;
        }
    }

}
