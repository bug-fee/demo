package com.wonders.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
import org.springframework.web.context.annotation.SessionScope;

import java.net.URI;

@RestController
@SessionScope
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    protected static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping(value = "/findById", params = "id")
    //添加hystrix请求超时回调函数,hystrix默认请求超时为1秒如果请求得不到响应将执行回调函数
    //propagation设置hystrix隔离级别为线程隔离级别，使用同一个线程执行超时回调函数
    //1.隔离策略(1)线程池隔离(2)请求数量隔离
//    1）采用线程池隔离配置，findById和failureFallback方法是在一个独立线程中调用；这在绑定本地线程时很有用UserContext
//    2）采用的策略是SEMAPHORE，不配置的话，默认是在不同的线程中供你调用；
    //PS:当传播策略出现异常时在当前controller类上添加作用域@SessionScope、@RequestScope等，默认不配置作用域
    @HystrixCommand(fallbackMethod = "failureFallback", commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
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
