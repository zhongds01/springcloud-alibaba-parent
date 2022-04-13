package com.zds.properties;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 白名单过滤器
 *
 * @author zhongdongsheng
 * @since 2022/4/13
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.auth")
public class PermissionProperties {
    private List<String> whiteIP;

    private List<String> whiteUrl;
}
