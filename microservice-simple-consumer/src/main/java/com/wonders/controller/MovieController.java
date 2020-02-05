package com.wonders.controller;

import com.wonders.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
//RestController是组合注解相当于加了：@Controller、@ResponseBody
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    //注入yml中的配置
    @Value("${user.userServicePath}")
    private String userServicePath;

    //@PostMapping("/simple/{id}")//相当于post请求
    @GetMapping(value = "/findById", params = "id")//getMapping等于下面这一行代码，相对于简写了。
    //@RequestMapping(value = "/simple/{id}", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public User findById(@RequestParam("id") Long id) {
        return this.restTemplate.getForObject(userServicePath + id, User.class);
    }

    @GetMapping("/demo")
    public String demo(HttpServletRequest request) {
        logger.debug(String.format("remote port : %d", request.getRemotePort()));
        return request.getLocalPort()+"";
    }
}
