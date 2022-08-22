package com.xzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 表名此服务是Eure客户端，开启Eureka客户端功能，不加此注解默认也开启客户端功能
@EnableEurekaClient
@MapperScan("com.xzz.*.mapper")//扫描多模块的mapper接口
public class StudentServerApp {

    public static void main(String[] args) {
        SpringApplication.run(StudentServerApp.class, args);
    }


}