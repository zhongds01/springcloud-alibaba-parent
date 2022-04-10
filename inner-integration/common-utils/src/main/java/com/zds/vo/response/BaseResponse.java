package com.zds.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * BaseResponse
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BaseResponse {
    private String code;

    private String message;

    @Override
    public String toString() {
        return new StringJoiner(", ", BaseResponse.class.getSimpleName() + "[", "]")
            .add("code='" + code + "'")
            .add("message='" + message + "'")
            .toString();
    }
}
