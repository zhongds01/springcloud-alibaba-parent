package com.zds.exception.handle;

import com.zds.vo.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GlobalExceptionHandler
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger DEBUG_LOG = LoggerFactory.getLogger("debugLogger");

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception e) {
        DEBUG_LOG.error("[Global Exception] exception is: {}", e.toString());
        return new BaseResponse("400", "Service Unavailable");
    }
}
