package com.zds.interceptor;

import java.io.IOException;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * RestClientHttpRequestInterceptor
 *
 * @author zhongdongsheng
 * @since 2022/4/20
 */
public class RestHttpClientRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
        ClientHttpRequestExecution execution) throws IOException {
        String traceID = MDC.get("traceID");
        request.getHeaders().set("Trace-ID", traceID);
        return execution.execute(request, body);
    }
}
