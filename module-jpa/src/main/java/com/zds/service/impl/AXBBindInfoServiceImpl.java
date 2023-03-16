package com.zds.service.impl;

import com.zds.entity.AXBBindInfo;
import com.zds.repository.AXBBindInfoDao;
import com.zds.service.AXBBindInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AXBBindInfoServiceImpl
 *
 * @author zhongdongsheng
 * @since 2023/3/14
 */
@Service
public class AXBBindInfoServiceImpl implements AXBBindInfoService {
    @Autowired
    private AXBBindInfoDao axbBindInfoDao;

//    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAXBBindInfo(AXBBindInfo axbBindInfo) {
        axbBindInfoDao.deleteByTelAAndTelX(axbBindInfo.getTelX());
        axbBindInfoDao.save(axbBindInfo);
    }
}
