package com.zds.grpc.server;

import com.zds.entity.Customer;
import com.zds.grpc.grpc.CustomerInfo;
import com.zds.grpc.grpc.CustomerRequest;
import com.zds.grpc.grpc.CustomerResponse;
import com.zds.grpc.grpc.api.CustomerInfoServiceGrpc;
import com.zds.grpc.interceptor.GrpcServerInterceptor;
import com.zds.service.CustomerService;
import io.grpc.stub.StreamObserver;
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
            request.getName());
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
        DEBUG_LOGGER.info("[Grpc Invoke Finish], method: getCustomerInfoByName. request: {}",
            request.getName());
    }
}
