package com.zds.grpc.server;

import com.zds.entity.Customer;
import com.zds.grpc.api.CustomerInfo;
import com.zds.grpc.api.CustomerInfoServiceGrpc;
import com.zds.grpc.api.CustomerRequest;
import com.zds.grpc.api.CustomerResponse;
import com.zds.grpc.constant.GrpcConstants.GrpcCode;
import com.zds.grpc.interceptor.GrpcServerInterceptor;
import com.zds.service.CustomerService;
import io.grpc.stub.StreamObserver;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * CustomerInfoService
 *
 * @author zhongdongsheng
 * @since 2022/3/29
 */
@GrpcService(interceptors = {GrpcServerInterceptor.class})
@RequiredArgsConstructor
public class CustomerInfoServer extends CustomerInfoServiceGrpc.CustomerInfoServiceImplBase {
    public static final Logger DEBUG_LOGGER = LoggerFactory.getLogger("debugLogger");

    private final CustomerService customerService;

    @Override
    public void getCustomerInfoByName(CustomerRequest request,
        StreamObserver<CustomerResponse> responseObserver) {
        DEBUG_LOGGER.info("[Grpc Invoke Start], method: getCustomerInfoByName. request: {}",
            request.getCustomerName());
        List<Customer> customers = customerService
            .selectOneCustomerByName(request.getCustomerName());
        CustomerResponse response;
        if (CollectionUtils.isEmpty(customers)) {
            response =
                CustomerResponse.newBuilder().setCode(GrpcCode.FAIL).build();
        } else {
            // 当前只返回第一条
            Customer customer = customers.get(0);
            response =
                CustomerResponse.newBuilder().setCode(GrpcCode.SUCCESS).setData(
                    CustomerInfo.newBuilder().setCustomerName(customer.getCustomerName())
                        .setCustomerSex(customer.getCustomerSex())
                        .setCustomerAddress(customer.getCustomerAddress())
                        .setCustomerEmail(customer.getCustomerEmail())
                        .setCustomerTel(customer.getCustomerTel())
//                        .setCreateTime(customer.getCreateTime().toString())
//                        .setUpdateTime(customer.getUpdateTime().toString())
                        .build()).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        DEBUG_LOGGER.info("[Grpc Invoke Finish], method: getCustomerInfoByName. request: {}",
            request.getCustomerName());
    }

    @Override
    public void saveCustomerInfo(CustomerRequest request,
        StreamObserver<CustomerResponse> responseObserver) {
        DEBUG_LOGGER.info("[Grpc Invoke Start], method: saveCustomerInfo. request: {}",
            request.toString());
        Customer customer = Customer.builder().id(1L).customerName(request.getCustomerName())
            .customerSex(request.getCustomerSex())
            .customerPassword(request.getPassword())
            .customerAddress(request.getCustomerAddress())
            .customerEmail(request.getCustomerEmail())
            .customerTel(request.getCustomerTel())
            .version(0)
            .createTime(new Date())
            .updateTime(new Date())
            .build();
        Integer row = customerService.saveInfo(customer);
        CustomerResponse response;
        if (row == null || row == 0) {
            response =
                CustomerResponse.newBuilder().setCode(GrpcCode.FAIL).build();
        } else {
            response =
                CustomerResponse.newBuilder().setCode(GrpcCode.SUCCESS).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        DEBUG_LOGGER.info("[Grpc Invoke Finish], method: saveCustomerInfo. request: {}",
            request.getCustomerName());
    }
}
