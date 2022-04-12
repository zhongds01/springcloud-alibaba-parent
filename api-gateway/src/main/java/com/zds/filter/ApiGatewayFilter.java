package com.zds.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关过滤器
 *
 * @author zhongdongsheng
 * @since 2022/4/10
 */
//@Component
//@Order(1)
public class ApiGatewayFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("first filter start");

        System.out.println("first filter finish, result: " + true);

        return chain.filter(exchange);
    }

}
