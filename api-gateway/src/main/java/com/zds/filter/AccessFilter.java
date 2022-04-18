package com.zds.filter;

import com.alibaba.fastjson.JSON;
import com.zds.constant.GatewayConstants;
import com.zds.enums.BaseResponseEnum;
import com.zds.properties.PermissionProperties;
import com.zds.properties.SecretKeyProperties;
import com.zds.util.auth.JwtUtils;
import com.zds.vo.response.BaseResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.Resource;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * AccessFilter实现统一鉴权
 *
 * @author zhongdongsheng
 * @since 2022/4/11
 */
@Component
@EnableConfigurationProperties(value = PermissionProperties.class)
public class AccessFilter implements GlobalFilter, Ordered {

    private static final Logger DEBUG_LOG = LoggerFactory.getLogger("debugLogger");

    @Resource
    private PermissionProperties permissionProperties;

    @Resource
    private SecretKeyProperties secretKeyProperties;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        com.zds.util.auth.DigestUtils.sha256("zhongdongsheng");
        // url白名单过滤
        boolean isWhiteUrl = checkWhiteUrl(exchange);
        if (isWhiteUrl) {
            DEBUG_LOG.info("Request url is in whiteUrl list");
            return chain.filter(exchange);
        }
        // ip白名单过滤
        boolean isWhiteIP = checkWhiteIP(exchange);
        if (isWhiteIP) {
            DEBUG_LOG.info("Request Ip is in whiteIP list");
            return chain.filter(exchange);
        }

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
                String sign = DigestUtils.md5DigestAsHex(authStr.getBytes(StandardCharsets.UTF_8));
                if (StringUtils.equals(sign, originSign)) {
                    DEBUG_LOG.info("Auth Success!");
                    return chain.filter(exchange);
                } else {
                    DEBUG_LOG.error("Auth Failed, sign Value is different");
                    return authFailed(exchange);
                }
            } else if (StringUtils.equals("JWT", authType)) {
                List<String> jwtToken = headers.get("JWTToken");
                String token = jwtToken != null ? jwtToken.get(0) : "";
                String verify = JwtUtils.verify(token);
                String s = new String(Base64Utils.decode(verify.getBytes(StandardCharsets.UTF_8)));
                if (StringUtils.isNotBlank(s)) {
                    DEBUG_LOG.info("Auth Success!");
                    return chain.filter(exchange);
                } else {
                    DEBUG_LOG.error("Auth Failed, token value is invalidate");
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

    private boolean checkWhiteUrl(ServerWebExchange exchange) {
        List<String> whiteUrls = permissionProperties.getWhiteUrl();
        return whiteUrls.stream()
            .anyMatch(
                whiteIP -> whiteIP.equals(exchange.getRequest().getPath().toString()));
    }

    private boolean checkWhiteIP(ServerWebExchange exchange) {
        List<String> whiteIPs = permissionProperties.getWhiteIP();
        return whiteIPs.stream()
            .anyMatch(
                whiteIP -> whiteIP.equals(exchange.getRequest().getRemoteAddress().getAddress()
                    .getHostAddress()));
    }

    private Mono<Void> authFailed(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        BaseResponse commonResponse = new BaseResponse(BaseResponseEnum.AUTH_FAILED.getCode(),
            BaseResponseEnum.AUTH_FAILED
                .getMessage());
        DataBuffer buffer = response.bufferFactory()
            .wrap(JSON.toJSONString(commonResponse).getBytes(StandardCharsets.UTF_8));

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(buffer));
    }

    private String sha256(String str) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(str + secretKeyProperties);
    }

    @Override
    public int getOrder() {
        return GatewayConstants.Ordered.ACCESS_FILTER;
    }
}
