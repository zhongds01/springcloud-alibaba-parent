package com.zds.grpc.server;

import com.zds.entity.Customer;
import com.zds.grpc.grpc.CustomerInfo;
import com.zds.grpc.grpc.CustomerRequest;
import com.zds.grpc.grpc.CustomerResponse;
import com.zds.grpc.grpc.api.CustomerInfoServiceGrpc;
import com.zds.service.CustomerService;
import io.grpc.stub.StreamObserver;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 * CustomerInfoService
 *
 * @author zhongdongsheng
 * @since 2022/3/29
 */
@GrpcService
@RequiredArgsConstructor
public class CustomerInfoServer extends CustomerInfoServiceGrpc.CustomerInfoServiceImplBase {

    private final CustomerService customerService;

    @Override
    public void getCustomerInfoByName(CustomerRequest request,
        StreamObserver<CustomerResponse> responseObserver) {
        List<Customer> customers = customerService.selectOneCustomerByName(request.getName());
        CustomerResponse response;
        if (CollectionUtils.isEmpty(customers)) {
            response =
                CustomerResponse.newBuilder().setCode("400").setData(
                    CustomerInfo.newBuilder().build()).build();
        } else {
            response =
                CustomerResponse.newBuilder().setCode("0").setData(
                    CustomerInfo.newBuilder().setName(customers.get(0).getCustomerName())
                        .setSex(customers.get(0).getCustomerSex()).build()).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
