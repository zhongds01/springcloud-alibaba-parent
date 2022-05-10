package com.zds.vo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CustomerInfoVO
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
public class CustomerInfoVO {
//    @NotBlank(message = "name is empty")
    private String name;

    private String tel;

    private String password;

    private String address;

    private String email;

    private String sex;
}
