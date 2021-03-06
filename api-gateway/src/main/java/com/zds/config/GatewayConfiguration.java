package com.zds.config;

import com.zds.exception.GatewayExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.WebExceptionHandler;

/**
 * GatewayConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/4/5
 */
@Configuration
public class GatewayConfiguration {

//    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(predicateSpec -> predicateSpec.path("/**").uri("lb://database-service"))
            .route(predicateSpec -> predicateSpec.path("/**").uri("lb://service-provider-7001"))
            .route(predicateSpec -> predicateSpec.path("/**").uri("lb://service-consumer-8001"))
            .build();
    }

    @Bean
    @Order(-1)
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new GatewayExceptionHandler();
    }
}
