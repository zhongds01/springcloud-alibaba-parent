package com.zds.notification.service;

import com.zds.vo.request.EmailRequest;

/**
 * EmailService
 *
 * @author zhongdongsheng
 * @since 2022/7/18
 */
public interface EmailService {
    void sendHtmlMail(EmailRequest emailRequest);
}
