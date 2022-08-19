package com.xzz.feignclient;

import com.xzz.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

//工厂方式的托底类
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    //托底方法
    @Override
    public UserFeignClient create(Throwable throwable) {
		//返回UserFeignClient 接口的实例
        return new UserFeignClient() {
            //降级方法
            @Override
            public User getById(Long id) {
                //托底数据
                return new User(-1L,"无此用户","用户服务不可用");
            }
        };
    }
}