package com.zds.config;

import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

/**
 * KafkaConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/5/4
 */
@Configuration
public class KafkaConfiguration {

    /**
     * 创建一个主题名为dataSave，1个分区，1个副本的主题
     *
     * @return
     */
    @Bean
    public NewTopic dataSaveTopic() {
        return new NewTopic("dataSave", 1, (short) 1);
    }

    /**
     * 配置支持批量消费的消费者监听工厂
     */
    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> map = kafkaProperties.buildConsumerProperties();
        ConcurrentKafkaListenerContainerFactory<String, String> factory
            = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(map));
        factory.getContainerProperties().setAckMode(AckMode.MANUAL);
        factory.setBatchListener(true);

        return factory;
    }
}
