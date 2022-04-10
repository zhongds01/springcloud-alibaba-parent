package com.zds.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CommonResponse
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CommonResponse<T> extends BaseResponse {

    private T data;

    public CommonResponse(String code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CommonResponse.class.getSimpleName() + "[", "]")
            .add("code=" + super.getCode())
            .add("message=" + super.getMessage())
            .add("data=" + data)
            .toString();
    }
}
