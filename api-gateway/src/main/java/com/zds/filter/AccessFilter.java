package com.zds.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.zds.vo.response.BaseResponse;
import com.zds.vo.response.CommonResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.transform.Result;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * AccessFilter实现统一鉴权
 *
 * @author zhongdongsheng
 * @since 2022/4/11
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered {
    private static final Logger DEBUG_LOG = LoggerFactory.getLogger("debugLogger");

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        // 请求头中包含MD5加密方式，服务器也通过MD5加密方式完成鉴权
        if (headers.containsKey("Authorization")) {
            List<String> authorization = headers.get("Authorization");
            String authType = authorization != null ? authorization.get(0) : "";
            if (StringUtils.equals("MD5", authType)) {
                List<String> appIDS = headers.get("AppID");
                String appID = appIDS != null ? appIDS.get(0) : "";
                // todo:根据appID查询对应的secret_key
                String secretKey = "ASDFGZDGBVNH";
                List<String> timestamps = headers.get("Timestamp");
                String timestamp = timestamps != null ? timestamps.get(0) : "";
                List<String> signList = headers.get("Sign");
                String originSign = signList != null ? signList.get(0) : "";
                String authStr = timestamp + "" + secretKey;
                String sign = MD5Utils.md5Hex((authStr).getBytes(StandardCharsets.UTF_8));
                if (StringUtils.equals(sign, originSign)) {
                    DEBUG_LOG.info("Auth Success!");
                    return chain.filter(exchange);
                } else {
                    DEBUG_LOG.error("Auth Failed, sign Value is different");
                    return authFailed(exchange);
                }
            } else {
                // 请求头中没传加密类型，返回失败
                DEBUG_LOG.error("Auth Failed, Authorization Value Has No AuthType");
                return authFailed(exchange);
            }
        } else {
            DEBUG_LOG.error("Auth Failed, Request Header Has No Authorization");
            return authFailed(exchange);
        }
    }

    private Mono<Void> authFailed(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        BaseResponse commonResponse = new BaseResponse();
        commonResponse.setCode("90000401");
        commonResponse.setMessage("鉴权失败");
        DataBuffer buffer = exchange.getResponse().bufferFactory()
            .wrap(JSON.toJSONString(commonResponse).getBytes(StandardCharsets.UTF_8));

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
