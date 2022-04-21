package com.zds.exception;

import com.alibaba.fastjson.JSON;
import com.zds.constant.GatewayConstants;
import com.zds.constant.GatewayConstants.RequestHeader;
import com.zds.enums.BaseResponseEnum;
import com.zds.vo.response.BaseResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关异常全局处理 GatewayExceptionHandler
 *
 * @author zhongdongsheng
 * @since 2022/4/14
 */
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger DEBUG_LOG = LoggerFactory.getLogger("debugLogger");

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpRequest request = exchange.getRequest();
        String traceID = Objects.requireNonNull(request.getHeaders().get(RequestHeader.TRACE_ID))
            .get(0);

        MDC.put(GatewayConstants.TRACE_ID, traceID);
        DEBUG_LOG.error("[ApiGateway Exception], request url is: {}, exception is: {}",
            request.getPath(), ex.getMessage());
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        BaseResponse baseResponse = new BaseResponse(BaseResponseEnum.SERVICE_UNAVAILABLE.getCode(),
            BaseResponseEnum.SERVICE_UNAVAILABLE
                .getMessage());
        DataBuffer buffer = response.bufferFactory()
            .wrap(JSON.toJSONString(baseResponse).getBytes(StandardCharsets.UTF_8));

        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(buffer));
    }
}
