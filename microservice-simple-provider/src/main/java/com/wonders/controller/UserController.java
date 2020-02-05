package com.wonders.controller;

import com.wonders.entity.User;
import com.wonders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//RestController是组合注解相当于加了：@Controller、@ResponseBody
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/simple/{id}")//getMapping等于下面这一行代码，相对于较简写spring4.3开始支持。
    //@RequestMapping(value = "/simple/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    //@PostMapping("/simple/{id}")//相当于post请求
    public User findById(@PathVariable Long id) {
        return userRepository.getOne(id);
    }

    @RequestMapping("/demo")
    @ResponseBody
    public String demo(){
        return "demo";
    }

}
