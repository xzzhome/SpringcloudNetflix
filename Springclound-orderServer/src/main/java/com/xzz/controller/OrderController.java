package com.xzz.controller;

import com.xzz.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/{id}")
    public User getById(@PathVariable("id") Long id){
        return restTemplate.getForObject("http://localhost:1020/user/"+id,User.class);
    }
}
