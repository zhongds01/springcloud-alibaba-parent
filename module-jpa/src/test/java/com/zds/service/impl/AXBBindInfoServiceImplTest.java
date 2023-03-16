package com.zds.service.impl;

import com.zds.entity.AXBBindInfo;
import com.zds.service.AXBBindInfoService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AXBBindInfoServiceImplTest
 *
 * @author zhongdongsheng
 * @since 2023/3/14
 */
@SpringBootTest
class AXBBindInfoServiceImplTest {
    @Autowired
    private AXBBindInfoService axbBindInfoService;

    @Test
    void updateAXBBindInfo() throws InterruptedException {
        ExecutorService executor =  Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            AXBBindInfo axbBindInfo = new AXBBindInfo();
            axbBindInfo.setId((long) i);
            axbBindInfo.setAppKey("appKey" + i);
            axbBindInfo.setTelA("1326090000" + i);
            axbBindInfo.setTelB("1315100000" + i);
            axbBindInfo.setTelX("1305100000" + i);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    axbBindInfoService.updateAXBBindInfo(axbBindInfo);
                }
            });
        }
        Thread.sleep(100000L);
    }
}