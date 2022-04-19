package com.zds.grpc.client;

import com.zds.grpc.grpc.CustomerInfo;
import com.zds.grpc.grpc.CustomerRequest;
import com.zds.grpc.grpc.CustomerResponse;
import com.zds.grpc.grpc.api.CustomerInfoServiceGrpc;
import com.zds.grpc.interceptor.GrpcClientInterceptor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * CustomerInfoService
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@Service
public class CustomerInfoService {

    @GrpcClient(value = "database-service", interceptors = {GrpcClientInterceptor.class})
    private CustomerInfoServiceGrpc.CustomerInfoServiceBlockingStub customerInfoServiceBlockingStub;

    public CustomerInfo queryCustomerInfoByName(String name) {
        CustomerRequest customerRequest = CustomerRequest.newBuilder().setName(name).build();
        CustomerResponse response = customerInfoServiceBlockingStub
            .getCustomerInfoByName(customerRequest);
        if (response.getCode().equals("0")) {
            return response.getData();
        }

        return null;
    }
}
