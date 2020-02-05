package com.wonders.client;

import com.wonders.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 设置一个feign的客户端实现类用于执行hystrix的断路器回调
 *
 * @author Wang si rui
 * @version 1.0
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 1、 <br>
 */
@Component
class HystrixClientFallback implements UserFeignClient {

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