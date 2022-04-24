package com.zds.controller;

import com.zds.enums.BaseResponseEnum;
import com.zds.grpc.client.RpcCustomerInfoService;
import com.zds.grpc.grpc.CustomerInfo;
import com.zds.service.CustomerInfoService;
import com.zds.vo.request.CustomerInfoVO;
import com.zds.vo.response.BaseResponse;
import com.zds.vo.response.CommonResponse;
import com.zds.vo.response.CustomerInfoData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");

    private final RpcCustomerInfoService rpcCustomerInfoService;

    private final CustomerInfoService customerInfoService;

    @PostMapping(path = "/customer/query")
    public CommonResponse<CustomerInfoData> queryCustomerInfo(
        @RequestBody @Validated CustomerInfoVO customerInfoVO) {
        DEBUG_LOGGER.info("Service-Consumer-7001 Receive request");
        CustomerInfo customerInfo = rpcCustomerInfoService
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

    @PostMapping(path = "/customer/async-notify")
    public BaseResponse asyncInvoke(@RequestBody CustomerInfoVO customerInfoVO) {
        customerInfoService
            .forwardNotify(customerInfoVO.getName(), customerInfoVO.getTel());

        return new BaseResponse(BaseResponseEnum.SUCCESS.getCode(), BaseResponseEnum.SUCCESS
            .getMessage());
    }
}
