package com.zds.controller;

import com.zds.enums.BaseResponseEnum;
import com.zds.grpc.api.CustomerInfo;
import com.zds.grpc.client.RpcCustomerInfoService;
import com.zds.service.CustomerInfoService;
import com.zds.vo.request.CustomerInfoVO;
import com.zds.vo.response.BaseResponse;
import com.zds.vo.response.CommonResponse;
import com.zds.vo.response.CustomerInfoData;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
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

    private final RedisTemplate<String, Object> redisTemplate;

    @PostMapping(path = "/customer/query")
    public CommonResponse<CustomerInfoData> queryCustomerInfo(
        @RequestBody @Validated CustomerInfoVO customerInfoVO) {
        DEBUG_LOGGER.info("Service-Consumer-7001 Receive request");
        CustomerInfo customerInfo = (CustomerInfo) redisTemplate.opsForValue()
            .get("customer-" + customerInfoVO.getName());
        CustomerInfoData customerInfoData = null;

        if (customerInfo != null) {
            customerInfoData = CustomerInfoData.builder().name(customerInfo.getCustomerName())
                .sex(customerInfo.getCustomerSex()).address(customerInfo.getCustomerAddress())
                .tel(customerInfo.getCustomerTel()).email(customerInfo.getCustomerEmail())
                .address(customerInfo.getCustomerAddress())
                .build();
        } else {
            customerInfo = rpcCustomerInfoService
                .queryCustomerInfoByName(customerInfoVO.getName());
            if (customerInfo != null) {
                customerInfoData = CustomerInfoData.builder().name(customerInfo.getCustomerName())
                    .sex(customerInfo.getCustomerSex()).address(customerInfo.getCustomerAddress())
                    .tel(customerInfo.getCustomerTel()).email(customerInfo.getCustomerEmail())
                    .address(customerInfo.getCustomerAddress())
                    .build();
                redisTemplate.opsForValue()
                    .set("customer-" + customerInfo.getCustomerName(), customerInfo, 60L, TimeUnit.SECONDS);
            }
        }

        return new CommonResponse<>(BaseResponseEnum.SUCCESS.getCode(),
            BaseResponseEnum.SUCCESS.getMessage(),
            customerInfoData);
    }

    @PostMapping(path = "/customer/async-notify")
    public BaseResponse asyncInvoke(@RequestBody CustomerInfoVO customerInfoVO) {
        customerInfoService
            .forwardNotify(customerInfoVO.getName(), customerInfoVO.getTel());

        return new BaseResponse(BaseResponseEnum.SUCCESS.getCode(), BaseResponseEnum.SUCCESS
            .getMessage());
    }

    @PostMapping(path = "/customer/save")
    public BaseResponse saveCustomerInfo(@RequestBody CustomerInfoVO customerInfoVO) {
        boolean result = rpcCustomerInfoService.saveCustomerInfo(customerInfoVO);
        if (result) {
            return new BaseResponse(BaseResponseEnum.SUCCESS.getCode(), BaseResponseEnum.SUCCESS
                .getMessage());
        }
        return new BaseResponse(BaseResponseEnum.FAILED.getCode(), BaseResponseEnum.FAILED
            .getMessage());

    }
}
