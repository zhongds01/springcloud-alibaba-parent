package com.zds.controller;

import com.zds.grpc.client.CustomerInfoService;
import com.zds.grpc.grpc.CustomerInfo;
import com.zds.vo.request.CustomerInfoVO;
import com.zds.vo.response.CommonResponse;
import com.zds.vo.response.CustomerInfoData;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomerController
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerInfoService customerInfoService;

    @PostMapping(path = "/customer/query")
    public CommonResponse<CustomerInfoData> queryCustomerInfo(
        @RequestBody @Validated CustomerInfoVO customerInfoVO) {
        CustomerInfo customerInfo = customerInfoService
            .queryCustomerInfoByName(customerInfoVO.getName());
        CustomerInfoData customerInfoData = null;
        if (customerInfo != null) {
            customerInfoData = new CustomerInfoData();
            customerInfoData.setName(customerInfo.getName());
            customerInfoData.setSex(customerInfo.getSex());
        }


        return new CommonResponse<>("0",
            "Success",
            customerInfoData);
    }
}
