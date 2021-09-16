package com.zds.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * service-provider提供的接口
 *
 * @author zhongdongsheng
 * @since 2021/9/16 22:37
 */
@RestController
public class HelloController {
    @GetMapping("/provider/hello/{value}")
    public String hello(@PathVariable String value) {
        return "Hello Nacos Discovery " + value;
    }
}
