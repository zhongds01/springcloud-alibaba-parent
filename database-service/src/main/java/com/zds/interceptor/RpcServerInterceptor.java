package com.zds.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import java.util.Map;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;

/**
 * RpcServerInterceptor
 *
 * @author zhongdongsheng
 * @since 2022/4/18
 */
public class RpcServerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
        Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();

        return null;
    }
}
