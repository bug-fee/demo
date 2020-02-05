package com.wonders.client;

import com.wonders.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
@FeignClient("microservice-provider-user")
public interface UserFeignClient {

    //要访问的服务端的接口地址
    @GetMapping(value = "/simple/{id}")
    public User findUserById(@PathVariable("id") Long id);

    @PostMapping(value="/user")
    public User postUser (@RequestBody User user);

    //此请求无效，feign在发送复杂对象时即使采用getMapping也会自动使用PostMapping发送参数，如果服务端以GetMapping接受参数则不被允许
    @GetMapping("/get-user")
    public User getUser(@RequestBody User user);
}
