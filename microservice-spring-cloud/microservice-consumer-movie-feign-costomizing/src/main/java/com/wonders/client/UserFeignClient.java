package com.wonders.client;

import com.wonders.config.FeignConfiguration;
import com.wonders.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

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
@FeignClient(name="microservice-provider-user",configuration = FeignConfiguration.class)
public interface UserFeignClient {

    //使用feign默认注解
    @RequestLine("GET /simple/{id}")
    public User findUserById(@Param("id") Long id);

}
