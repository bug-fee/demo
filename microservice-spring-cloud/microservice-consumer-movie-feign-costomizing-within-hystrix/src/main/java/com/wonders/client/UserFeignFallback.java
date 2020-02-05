package com.wonders.client;

import com.wonders.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 配置hystirx回调函数
 *
 * @author Wang si rui
 * @version 1.0
 * @lastAuthor 最后修改人
 * @lastDate最后时间
 * @Rewrite record 修改记录：
 * 1、 <br>
 */
@Component
public class UserFeignFallback implements UserFeignClient {
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