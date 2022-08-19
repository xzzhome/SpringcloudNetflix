package com.xzz.feignclient;

import com.xzz.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-server",fallbackFactory = UserFeignClientFallbackFactory.class)
//有一级路径还要加@RequestMapping("/user")
public interface UserFeignClient {

    //订单服务来调用这个方法      http://localhost:1020/user/10
    @GetMapping(value = "/user/{id}" )
    User getById(@PathVariable("id")Long id);
}