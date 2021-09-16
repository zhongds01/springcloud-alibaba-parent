package com.zds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhongdongsheng
 * @since 2021/9/16 22:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceConsumer7001Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumer7001Application.class, args);
    }

}
