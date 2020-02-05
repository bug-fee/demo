package com.wonders.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.wonders.entity.User;
import com.wonders.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController是组合注解相当于加了：@Controller、@ResponseBody
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    @GetMapping("/simple/{id}")//getMapping等于下面这一行代码，相对于较简写spring4.3开始支持。
    //@RequestMapping(value = "/simple/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    //@PostMapping("/simple/{id}")//相当于post请求
    public User findById(@PathVariable Long id) {
        logger.debug("simple provider serivce is active!");
        return userRepository.getOne(id);
    }

    //根据虚拟主机名称获取下一个服务端实例的主机地址(主机名称由应用名称全大写得来：建议从上下文spirng.application.name中获取并填入)
    @GetMapping(value = "/eureka-instance")
    public String serviceUrl() {
        InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
        return instance.getHostName();
    }

    //用于测试 ignored-patterns 忽略某些路径不进行反向代理
    @GetMapping(value = "/simple/admin/getAllUsers")
    public List<User> mangerPage() {
        return userRepository.findAll();
    }

    //根据虚拟主机名称获取所有服务端实例(主机名称由应用名称全大写得来：建议从上下文spirng.application.name中获取并填入)
    @GetMapping(value = "/instance-info")
    public List<ServiceInstance> showInfo() {
        logger.debug("-------------------");
        List<ServiceInstance> instances = this.discoveryClient.getInstances("microservice-provider-user");
        return instances;
    }

    @GetMapping(value = "/list-all")
    public List<User> listAll() {
        return userRepository.findAll();
    }


}
