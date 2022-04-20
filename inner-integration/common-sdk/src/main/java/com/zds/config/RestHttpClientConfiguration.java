package com.zds.config;

import com.zds.interceptor.RestHttpClientRequestInterceptor;
import java.util.Collections;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestHttpClientConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/4/20
 */
@Configuration
public class RestHttpClientConfiguration {
    @Bean
    @LoadBalanced
    public RestTemplate restHttpClientTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
            .setInterceptors(Collections.singletonList(new RestHttpClientRequestInterceptor()));

        return restTemplate;
    }
}
