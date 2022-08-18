package com.xzz.controller;

import com.xzz.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //订单服务来调用这个方法      http://localhost:1020/user/10
    @GetMapping( "/user/{id}" )
    public User getById(@PathVariable("id")Long id){
        //根据id去数据库查询User
        return  new User(id,"张三","我是张三");
    }
}