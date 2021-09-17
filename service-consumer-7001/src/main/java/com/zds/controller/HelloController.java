package com.zds.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final RestTemplate restTemplate;

    @Autowired
    public HelloController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/hello/{value}")
    public String hello(@PathVariable String value) {
        // 远程调用service-provider服务中的hello接口
        return restTemplate.getForObject("http://service-provider/provider/hello/" + value, String.class);
    }
}
