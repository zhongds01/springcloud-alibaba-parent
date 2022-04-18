package com.zds.constant;

/**
 * GatewayConstants
 *
 * @author zhongdongsheng
 * @since 2022/4/16
 */
public final class GatewayConstants {
    public static final String TRACE_ID = "traceID";

    public interface Ordered {

        int MDC_FILTER = 0;

        int ACCESS_FILTER = 1;
    }

    public interface RequestHeader{
        String TRACE_ID = "Trace-ID";
    }

}
