package com.xzz.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xzz.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@DefaultProperties(defaultFallback ="fallbackMethod")	//统一降级配置
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackMethod")   //方法熔断
    @GetMapping("/order/{id}")
    public User getById(@PathVariable("id") Long id){
        String url = "http://user-server/user/"+id;
        return restTemplate.getForObject(url,User.class);
    }

    //降级方法 ， 参数和返回值必须和被熔断的方法一致 ，方法名要和fallbackMethod 的值一致
    public User fallbackMethod(@PathVariable("id")Long id){
        //返回托底数据
        return new User(-1L ,"无此用户","用户服务不可用");
    }
}
