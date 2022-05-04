package com.zds.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * KafkaConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/5/4
 */
@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic dataSaveTopic() {
        return new NewTopic("dataSave", 1, (short) 1);
    }

}
