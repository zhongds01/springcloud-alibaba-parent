package com.zds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * HelloController
 *
 * @author zhongdongsheng
 * @since 2021/9/16 22:34
 */
@RestController
public class HelloController {
    public static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restHttpClientTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/hello/{value}")
    public String hello(@PathVariable String value) {
        DEBUG_LOGGER.info("Service-Consumer-7001 Receive request, requestParam is: {}", value);
        // 远程调用service-provider服务中的hello接口
        return restHttpClientTemplate
            .getForObject("http://service-provider/provider/hello/" + value, String.class);
    }
}
