package com.zds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * CustomerInfoService
 *
 * @author zhongdongsheng
 * @since 2022/4/24
 */
@Service
public class CustomerInfoService {
    public static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");

    @Async("asyncTaskExecutor")
    public void forwardNotify(String name, String tel) {
        try {
            // 模拟耗时
            Thread.sleep(10000L);
            DEBUG_LOGGER.info("notify name: {}, tel: {}", name, tel);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
