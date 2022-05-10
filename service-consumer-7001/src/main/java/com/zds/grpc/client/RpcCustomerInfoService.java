package com.zds.grpc.client;

import com.zds.grpc.api.CustomerInfo;
import com.zds.grpc.api.CustomerInfoServiceGrpc;
import com.zds.grpc.api.CustomerRequest;
import com.zds.grpc.api.CustomerResponse;
import com.zds.grpc.constant.GrpcConstants.GrpcCode;
import com.zds.grpc.interceptor.GrpcClientInterceptor;
import com.zds.vo.request.CustomerInfoVO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * CustomerInfoService
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@Service
public class RpcCustomerInfoService {

    @GrpcClient(value = "database-service", interceptors = {GrpcClientInterceptor.class})
    private CustomerInfoServiceGrpc.CustomerInfoServiceBlockingStub customerInfoServiceBlockingStub;

    public CustomerInfo queryCustomerInfoByName(String name) {
        CustomerRequest customerRequest = CustomerRequest.newBuilder().setCustomerName(name).build();
        CustomerResponse response = customerInfoServiceBlockingStub
            .getCustomerInfoByName(customerRequest);
        if (response.getCode().equals(GrpcCode.SUCCESS)) {
            return response.getData();
        }

        return null;
    }

    public boolean saveCustomerInfo(CustomerInfoVO customerInfoVO) {
        CustomerRequest customerRequest = CustomerRequest.newBuilder()
            .setCustomerName(customerInfoVO.getName())
            .setCustomerEmail(customerInfoVO.getEmail())
            .setCustomerAddress(customerInfoVO.getAddress())
            .setCustomerSex(customerInfoVO.getSex())
            .setCustomerTel(customerInfoVO.getTel())
            .setPassword(customerInfoVO.getPassword())
            .build();
        CustomerResponse response = customerInfoServiceBlockingStub
            .saveCustomerInfo(customerRequest);

        return response.getCode().equals(GrpcCode.SUCCESS);
    }
}
