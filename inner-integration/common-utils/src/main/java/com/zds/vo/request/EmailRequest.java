package com.zds.vo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * EmailRequest
 *
 * @author zhongdongsheng
 * @since 2022/7/18
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class EmailRequest {
    private String name;

    private String password;

    private String subject;

    private String sendTo;

    private String content;
}
