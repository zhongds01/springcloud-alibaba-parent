package com.zds.repository;

import com.zds.entity.AXBBindInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * AXBBindInfoDao
 *
 * @author zhongdongsheng
 * @since 2023/3/14
 */
@Repository
public interface AXBBindInfoDao extends JpaRepository<AXBBindInfo, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from axb_bind_info where tel_x = ?1", nativeQuery = true)
    int deleteByTelAAndTelX( String telX);
}
