package com.zds.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AXBBindInfo
 *
 * @author zhongdongsheng
 * @since 2023/3/14
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "axb_bind_info", indexes = {@Index(name = "axb_bind_a_x", columnList = "tel_a, tel_x", unique = true),
    @Index(name = "axb_bind_b_x", columnList = "tel_b, tel_x", unique = true)})
public class AXBBindInfo {
    @Id
    private Long id;

    @Column(name = "tel_a", length = 64, nullable = false)
    private String telA;

    @Column(name = "tel_b", length = 64, nullable = false)
    private String telB;

    @Column(name = "tel_x", length = 64, nullable = false)
    private String telX;

    @Column(name = "expire_time", length = 64)
    private String expireTime;

    @Column(name = "app_key", length = 64, nullable = false)
    private String appKey;
}
