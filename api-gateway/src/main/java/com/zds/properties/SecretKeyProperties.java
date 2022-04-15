package com.zds.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SecretKeyProperties
 *
 * @author zhongdongsheng
 * @since 2022/4/15
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "security.key")
public class SecretKeyProperties {
    private String sha256;
}
