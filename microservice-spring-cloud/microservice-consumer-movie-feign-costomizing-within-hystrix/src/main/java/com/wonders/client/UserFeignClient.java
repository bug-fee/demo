package com.wonders.client;

import com.wonders.config.FeignConfiguration;
import com.wonders.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

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
//添加feign支持代替restTemplate,填写要访问的服务端应用名称；此处应从配置文件中读取:"${serverName}"
//configuration使用自定以的feign配置类
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class, fallback = UserFeignClient.UserFeignFallback.class)
public interface UserFeignClient {

    //使用feign默认注解
    @RequestLine("GET /simple/{id}")
    public User findUserById(@Param("id") Long id);

    /**
     * 定义一个hystrix回调类必须实现UserFeignClient，
     * 如果没有添加spring-cloud-starter-hystrix依赖的话，必须在这个默认类上添加@Compoent注解，否则会报错
     *
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    @Component
    class UserFeignFallback implements UserFeignClient {

        @Override
        public User findUserById(Long id) {
            User user = new User();
            user.setId(0L);
            user.setName("小马");
            user.setBalance(new BigDecimal(0));
            user.setAge((short) 1);
            return user;
        }
    }


}
