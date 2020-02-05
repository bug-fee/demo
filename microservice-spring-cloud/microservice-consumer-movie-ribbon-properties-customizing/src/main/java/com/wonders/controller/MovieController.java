package com.wonders.controller;

import com.wonders.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//RestController是组合注解相当于加了：@Controller、@ResponseBody
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    protected static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    //注入yml中的配置
    @Value("${user.userServicePath}")
    private String userServicePath;

    //@PostMapping("/simple/{id}")//相当于post请求
    @GetMapping(value = "/findById", params = "id")//getMapping等于下面这一行代码，相对于简写了。(params = "id"请求参数限定：必须传入该参数)
    //@RequestMapping(value = "/simple/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
      public User findById(@RequestParam("id") Long id) {
//    public User findById(@PathVariable("id") Long id) {
//        return this.restTemplate.getForObject(userServicePath+id, User.class);

        //根据applicationName获取正在访问的微服务提供者信息
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
        logger.debug("port:"+serviceInstance.getPort());
        //logger.debug("host:"+serviceInstance.getHost());
        //logger.debug("serviceId:"+serviceInstance.getServiceId());

        //VIP virtual IP:一种负载均衡技术;一般的外网域名解析是通过DNS解析完成(ip+mac物理网卡);但是VIP是不绑定mac物理网卡的，所以在程序上可以控制访问指定的机器(mac物理网卡)。
        //VIP主要产品有：HAProxy、Heartbeat
        //映射到Eureka服务器上地址应为http://localhost:7900/simple/；此处使用VIP映射(也就是服务提供者应用名称：microservice-provider-user)
        // 和restTemplate(加了@LoadBalanced注解)达到负载均衡的效果
        return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
    }

    @GetMapping("/demo1")
    public String demo() {
        return "demo";
    }
}
