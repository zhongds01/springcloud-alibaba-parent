package com.zds.controller;

import com.zds.properties.NacosProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author zhongdongsheng
 * @since 2021/9/17 20:01
 */
@RestController
@RequestMapping("/config")
@RefreshScope
@EnableConfigurationProperties({NacosProperties.class})
public class ConfigController {
    private final Environment environment;

    private final NacosProperties properties;

    public ConfigController(NacosProperties properties,Environment environment) {
        this.properties = properties;
        this.environment = environment;
    }

    @Value("${nacos.test.server-url}")
    private String serverUrl;

//    @Value("${name}")
//    private String name;

//    @Value("${group}")
//    private String group;

//    @Value("${nacos.test.port}")
//    private String port;
//
//    @Value("${nacos.test.alive}")
//    private boolean alive;
//
//    @Value("${user.name}")
//    private String userName;
//
//    @Value("${shared}")
//    private boolean shared;
//
//    @Value("${user.namespace}")
//    private String nameSpace;

    @GetMapping("/get")
    public String get() {
        return "serverUrl" + ":" + "port" + " status is : " + "alive";
//        return properties.getServerUrl() + ":" + properties.getPort() + " status is : " + properties.isAlive();
    }
}
