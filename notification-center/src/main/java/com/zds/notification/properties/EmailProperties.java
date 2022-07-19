package com.zds.notification.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * EmailProperties
 *
 * @author zhongdongsheng
 * @since 2022/7/19
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {
    private String sendTo;

    private String sendFrom;
}
