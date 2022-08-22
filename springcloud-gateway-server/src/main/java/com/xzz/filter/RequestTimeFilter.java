package com.xzz.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class RequestTimeFilter implements GatewayFilter, Ordered {
    //使用slf4j日志器，打印日志
    private static final Logger log= LoggerFactory.getLogger(RequestTimeFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //打印日志
        String path = exchange.getRequest().getURI().getPath();
        log.info("当前请求路径为：{}",path);
        //开始时间
        exchange.getAttributes().put("countStartTime", System.currentTimeMillis());
        //执行完成之后
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    //开始时间
                    Long startTime = exchange.getAttribute("countStartTime");
                    //结束时间
                    Long endTime=(System.currentTimeMillis() - startTime);
                    if (startTime != null) {
                        //打印日志
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + endTime + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}