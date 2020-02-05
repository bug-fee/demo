package com.wonders.controller;

import com.wonders.client.FeignClient2;
import com.wonders.client.UserFeignClient;
import com.wonders.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private FeignClient2 feignClient2;

    /**
     * microservice-provider-user
     * @param serviceName
     * @return
     */
    @RequestMapping(value = "/{serviceName}", method = RequestMethod.GET)
    public String findEurekaByServiceName(@PathVariable("serviceName") String serviceName) {
        return this.feignClient2.findServiceInfoFromEurekaByServiceName(serviceName);
    }

    @RequestMapping(value = "/eurekaHealth", method = RequestMethod.GET)
    public String findEurekaByServiceName(){
        return this.feignClient2.health();
    }

    @GetMapping(value = "/demo")
    public String demo() {
        return "demo";
    }
}

