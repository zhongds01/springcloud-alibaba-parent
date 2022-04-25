package com.zds.config;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * ThreadContextDecorator
 *
 * @author zhongdongsheng
 * @since 2022/4/25
 */
public class ThreadContextDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
            SecurityContext context = SecurityContextHolder.getContext();
            return ()->{
                try {
                    RequestContextHolder.setRequestAttributes(requestAttributes);
                    MDC.setContextMap(copyOfContextMap);
                    SecurityContextHolder.setContext(context);
                    runnable.run();
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                    MDC.clear();
                    SecurityContextHolder.clearContext();
                }
            };
        } catch (Exception e) {
            return runnable;
        }
    }
}
