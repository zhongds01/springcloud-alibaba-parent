package com.zds.filter;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.zds.constant.GatewayConstants;
import com.zds.constant.GatewayConstants.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关过滤器,通过MDC实现日志链路追踪
 *
 * @author zhongdongsheng
 * @since 2022/4/10
 */
@Component
public class ApiGatewayFilter implements GlobalFilter, Ordered {

    private static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uuid = UuidUtils.generateUuid().replace("-", "").substring(0, 16);
        MDC.put(GatewayConstants.TRACE_ID, uuid);
        ServerHttpRequest request = exchange.getRequest();
        // 设置请求头
        ServerHttpRequest build = request.mutate().header(RequestHeader.TRACE_ID, uuid).build();

        return chain.filter(exchange.mutate().request(build).build());
    }

    @Override
    public int getOrder() {
        return GatewayConstants.Ordered.MDC_FILTER;
    }
}
