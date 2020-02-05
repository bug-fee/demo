package com.wonders.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wonders.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    protected static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping(value = "/findById", params = "id")
    //添加hystrix请求超时回调函数,hystrix默认请求超时为1秒,如果请求得不到响应将执行回调函数
    @HystrixCommand(fallbackMethod = "failureFallback")
    public User findById(@RequestParam("id") Long id) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        logger.debug("port:" + serviceInstance.getPort());
        URI storeUri = URI.create(String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()));
        return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
    }

    /**
     * 定义失败回调函数<p/>
     * 应与调用方返回的参数和入参一致
     *
     * @author Wang si rui
     */
    public User failureFallback(Long id) {
        Short age = 1;
        User user = new User();
        user.setId(0L);
        user.setAge(age);
        user.setName("tony");
        return user;
    }

    @GetMapping("/demo1")
    public String demo() {
        return "demo";
    }
}
