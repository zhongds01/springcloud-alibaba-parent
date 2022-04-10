package com.zds;

import com.zds.util.map.MapUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhongdongsheng
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zds.mapper")
@EnableDiscoveryClient
public class DatabaseServiceApplication {
    private static Map<String,Object> ORIGIN_MAP = new HashMap<>();

    static {
        ORIGIN_MAP.put("1","1");
        ORIGIN_MAP.put("2","2");
        ORIGIN_MAP.put("5","5");
        ORIGIN_MAP.put("3","3");
        ORIGIN_MAP.put("4","4");
        ORIGIN_MAP = Collections.unmodifiableMap(ORIGIN_MAP);
    }

    public static void main(String[] args) {
        String s = MapUtils.convertMapToOrderedString(ORIGIN_MAP);
        SpringApplication.run(DatabaseServiceApplication.class, args);
    }

}
