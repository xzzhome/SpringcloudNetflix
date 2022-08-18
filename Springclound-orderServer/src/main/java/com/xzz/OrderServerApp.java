package com.xzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// 表名此服务是Eure客户端，开启Eureka客户端功能，不加此注解默认也开启客户端功能
@EnableEurekaClient
public class OrderServerApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApp.class, args);
    }

    //配置一个RestTemplate ，Spring封装的一个机遇Restful风格的http客户端 工具
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}