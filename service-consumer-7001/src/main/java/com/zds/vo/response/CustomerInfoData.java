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
 * CustomerInfoData
 *
 * @author zhongdongsheng
 * @since 2022/4/1
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class CustomerInfoData {

    private String name;

    private String password;

    private String sex;

    private String tel;

    private String email;

    private String address;

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomerInfoData.class.getSimpleName() + "[", "]")
            .add("name='" + name + "'")
            .add("password='" + "******" + "'")
            .add("sex='" + sex + "'")
            .add("tel='" + tel + "'")
            .add("email='" + email + "'")
            .add("address='" + address + "'")
            .toString();
    }
}
