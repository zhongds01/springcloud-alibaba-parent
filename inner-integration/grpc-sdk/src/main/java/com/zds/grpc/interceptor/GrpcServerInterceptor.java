package com.zds.grpc.interceptor;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.slf4j.MDC;

/**
 * GrpcServerInterceptor
 *
 * @author zhongdongsheng
 * @since 2022/4/19
 */
@GrpcGlobalServerInterceptor
public class GrpcServerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
        Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        // 获取客户端参数
        Metadata.Key<String> TRACE_ID = Metadata.Key
            .of("traceID", Metadata.ASCII_STRING_MARSHALLER);
        String traceID = metadata.get(TRACE_ID);
        MDC.put("traceID", traceID);

        return serverCallHandler.startCall(new SimpleForwardingServerCall<ReqT, RespT>(serverCall) {
            @Override
            public void sendHeaders(Metadata headers) {
                super.sendHeaders(headers);
            }
        }, metadata);
    }
}
