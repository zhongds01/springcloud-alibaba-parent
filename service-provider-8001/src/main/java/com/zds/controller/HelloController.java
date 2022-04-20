package com.zds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");

    @GetMapping("/provider/hello/{value}")
    public String hello(@PathVariable String value) {
        DEBUG_LOGGER.info("Service-provider-8001 Receive request, requestParam is: {}", value);
        return "Hello Nacos Discovery " + value;
    }

}
