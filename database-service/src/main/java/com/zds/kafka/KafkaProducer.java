package com.zds.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * KafkaProducer
 *
 * @author zhongdongsheng
 * @since 2022/5/4
 */
@Service
public class KafkaProducer {

    public static final Logger KAFKA_LOGGER = LoggerFactory.getLogger("kafkaLogger");

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     *
     * @param topic 主题
     * @param partition 分区，当前一个分区，所以是0
     * @param key key
     * @param dataJson 数据
     */
    public void sendMessage(String topic, Integer partition, String key, String dataJson) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
            .send(topic, partition, key, dataJson);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                KAFKA_LOGGER
                    .error("Failed to send kafka message, topic is: {}, errorMessage is: {}", topic,
                        ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                KAFKA_LOGGER
                    .info("Success to send kafka message, topic is: {}, offset is: {}", topic,
                        result.getRecordMetadata().offset());
            }
        });
    }
}
