package com.xzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 表名此服务是Eure客户端，开启Eureka客户端功能，不加此注解默认也开启客户端功能
@EnableEurekaClient
@EnableFeignClients
public class PayServerApp {

    public static void main(String[] args) {
        SpringApplication.run(PayServerApp.class, args);
    }


}