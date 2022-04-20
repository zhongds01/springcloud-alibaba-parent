package com.zds.config;

import com.zds.filter.TraceIDFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/4/18
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<TraceIDFilter> filterRegistrationBean() {
        FilterRegistrationBean<TraceIDFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new TraceIDFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
