package com.zds.service.impl;

import com.zds.entity.Customer;
import com.zds.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CustomerServiceImplTest
 *
 * @author zhongdongsheng
 * @since 2022/3/31
 */
@SpringBootTest
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void selectOneCustomerByName() {
        List<Customer> customers = customerService.selectOneCustomerByName("zhongdongsheng");
        customers.forEach(System.out::println);
    }
}