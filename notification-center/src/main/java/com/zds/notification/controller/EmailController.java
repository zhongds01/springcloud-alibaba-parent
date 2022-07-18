package com.zds.notification.controller;

import com.zds.vo.request.EmailRequest;
import com.zds.vo.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmailController
 *
 * @author zhongdongsheng
 * @since 2022/7/18
 */
@RestController
public class EmailController {
    @Autowired
    private EmailSerice emailSerice;

    @PostMapping(path = "/user/notification/email")
    public BaseResponse sendEmail(EmailRequest emailRequest) {

    }
}
