package com.zds.service;

import com.alibaba.fastjson.JSON;
import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * KafkaService
 *
 * @author zhongdongsheng
 * @since 2022/5/4
 */
@Service
public class KafkaService {

    public static final Logger KAFKA_LOGGER = LoggerFactory.getLogger("kafkaLogger");

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 不开启批量处理
     *
     * @param records
     * @param ack
     */
    @KafkaListener(topics = {"dataSave"})
    public void processDataSave(ConsumerRecord<String, String> records, Acknowledgment ack) {
        KAFKA_LOGGER.info("[Topic]: {}, begin to process", "dataSave");
        if (records == null) {
            KAFKA_LOGGER.error("[Topic]: {}, records size is 0", "dataSave");
            return;
        }

        KAFKA_LOGGER.info("[Topic]: {}, begin to consumer", "dataSave");
        Customer customer = JSON.parseObject(records.value(), Customer.class);
        customerMapper.insertCustomer(customer);

        ack.acknowledge();
        KAFKA_LOGGER.info("[Topic]: {}, end to process", "dataSave");
    }

    /**
     * 开启批量处理,需要手动配置containerFactory
     *
     * @param records records
     * @param ack     ack
     */
    @KafkaListener(topics = {"dataSave"}, containerFactory = "batchFactory")
    public void batchProcessDataSave(ConsumerRecords<String, String> records, Acknowledgment ack) {
        KAFKA_LOGGER.info("[Topic]: {}, begin to batch process", "dataSave");
        if (records.isEmpty()) {
            KAFKA_LOGGER.error("[Topic]: {}, records size is 0", "dataSave");
            return;
        }

        records.forEach(record -> {
            KAFKA_LOGGER.info("[Topic]: {}, begin to consumer", "dataSave");
            Customer customer = JSON.parseObject(record.value(), Customer.class);
            customerMapper.insertCustomer(customer);
        });

        ack.acknowledge();
        KAFKA_LOGGER.info("[Topic]: {}, end to batch process", "dataSave");
    }

}
