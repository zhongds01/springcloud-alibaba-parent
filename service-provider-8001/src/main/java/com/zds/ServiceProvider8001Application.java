package com.zds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhongdongsheng
 * @since 2021/9/16 22:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
public class ServiceProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider8001Application.class, args);
    }

}
