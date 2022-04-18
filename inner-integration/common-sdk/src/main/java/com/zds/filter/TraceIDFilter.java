package com.zds.filter;

import com.zds.constant.CommonConstants;
import com.zds.constant.CommonConstants.RequestHeader;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * TraceIDFilter
 *
 * @author zhongdongsheng
 * @since 2022/4/18
 */
public class TraceIDFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(RequestHeader.TRACE_ID);
        MDC.put(CommonConstants.TRACE_ID, header);
        filterChain.doFilter(request, response);
    }
}
