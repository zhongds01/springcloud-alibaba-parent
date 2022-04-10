package com.zds.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * NacosProperties
 *
 * @author zhongdongsheng
 * @since 2021/9/17 21:34
 */
@Data
@ConfigurationProperties(prefix = "nacos.test")
public class NacosProperties {
    private String serverUrl;
    private String port;
    private boolean alive;
}
