package com.wonders.controller;

import com.wonders.client.UserFeignClient;
import com.wonders.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/findById", params = "id")
      public User findById(@RequestParam("id") Long id) {
        return this.userFeignClient.findUserById(id);
    }

    @GetMapping(value="/testPostUser")
    public User postUser(){
        User user=new User();
        user.setAge((short) 23);
        user.setBalance(new BigDecimal(1000));
        return this.userFeignClient.postUser(user);
    }

    @GetMapping(value="/testGetUser")
    public User getUser(){
        User user=new User();
        user.setAge((short) 23);
        user.setBalance(new BigDecimal(1000));
        return this.userFeignClient.getUser(user);
    }
}
