package com.zds.enums;

/**
 * BaseResponseEnum
 *
 * @author zhongdongsheng
 * @since 2022/4/14
 */
public enum BaseResponseEnum {
    SERVICE_UNAVAILABLE("7100400", "Service Unavailable"),
    SUCCESS("7100200", "Success"),
    AUTH_FAILED("7100401", "Auth Failed"),
    FAILED("7100500", "Failed");

    final String code;

    final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    BaseResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
