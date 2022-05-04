package com.zds.service.impl;

import com.alibaba.fastjson.JSON;
import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import com.zds.service.CustomerService;
import com.zds.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public List<Customer> selectOneCustomerById(Long id) {
        return customerMapper.selectOneCustomerById(id);
    }


    /**
     * 开启事务,验证该方法执行过程中报错是否会自动回滚
     * 如果不使用事务注解开启事务管理，该方法会成功插入数据
     * 如果使用注解开启事务，则会自动回滚之前的插入操作
     *
     * @param customer custom
     * @return int
     */
    // @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insertCustomer(Customer customer) {
        int rows = customerMapper.insertCustomer(customer);
         return rows;
    }

    @Override
    public Integer updateCustomerById(Customer customer) {
        return customerMapper.updateCustomerById(customer);
    }

    @Override
    public List<Customer> selectOneCustomer() {
        return customerMapper.selectCustomer();
    }

    @Override
    public List<Customer> selectOneCustomerByName(String name) {
        return customerMapper.selectOneCustomerByName(name);
    }

    @Override
    public Integer saveInfo(Customer customer) {
        kafkaProducer
            .sendMessage("dataSave", 1, String.valueOf(customer.getId()),
                JSON.toJSONString(customer));
        return 1;
    }
}
