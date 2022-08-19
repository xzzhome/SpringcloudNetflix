package com.xzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient         //开启Eureka客户端功能
@EnableCircuitBreaker       //开启Hystrix熔断
public class OrderServerApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApp.class, args);
    }

    //配置一个RestTemplate ，Spring封装的一个机遇Restful风格的http客户端 工具
    @Bean
    @LoadBalanced//让RestTemplate有负载均衡的功能
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}