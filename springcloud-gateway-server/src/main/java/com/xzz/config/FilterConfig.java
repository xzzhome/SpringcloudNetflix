package com.xzz.config;

import com.xzz.filter.RequestTimeFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    //配置Filter作用于那个访问规则上
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes().route(r -> r.path("/servers/user/**")
                //去掉2个前缀
                        .filters(f -> f.stripPrefix(2)
                        .filter(new RequestTimeFilter())
                        .addResponseHeader("X-Response-test", "test"))
                        .uri("lb://user-server")
                        .order(0)
                        .id("test-RequestTimeFilter")
                ).build();
    }
}
