package com.wonders.client;

import com.wonders.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181223
 * @Rewrite record：
 * 1、
 */
//创建hystrix 回调工厂类控制请求失败原因
@Component
public class HystrixClientFactory implements FallbackFactory<UserFeignClient> {
    Logger LOOGER = LoggerFactory.getLogger(HystrixClientFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        //slf4j支持占位符可以使用{}
        //当请求失败时记录日志
        //返回：Hystrix circuit short-circuited and is OPEN  //fallback reason was：null
        LOOGER.info("fallback reason was：{}", throwable.getMessage());

        return new UserFeignClientWithFactory() {
            @Override
            public User findUserById(Long id) {
                User user = new User();
                user.setAge((short) 0);
                user.setId(-1L);
                user.setName("小朱");
                return user;
            }
        };
    }
}
