package com.zds.grpc.interceptor;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.slf4j.MDC;

/**
 * GrpcClientInterceptor
 *
 * @author zhongdongsheng
 * @since 2022/4/19
 */
@GrpcGlobalClientInterceptor
public class GrpcClientInterceptor implements ClientInterceptor {

    static final Metadata.Key<String> TRACE_ID = Metadata.Key
        .of("traceID", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
        MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
            channel.newCall(methodDescriptor, callOptions)) {
            final String traceID = MDC.get("traceID");
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(TRACE_ID, traceID);
                super.start(new SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onHeaders(Metadata headers) {
                        super.onHeaders(headers);
                    }
                }, headers);
            }
        };
    }
}
