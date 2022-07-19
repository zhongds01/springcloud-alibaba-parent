package com.zds.notification.service;

import com.zds.vo.request.EmailRequest;
import javax.mail.MessagingException;

/**
 * EmailService
 *
 * @author zhongdongsheng
 * @since 2022/7/18
 */
public interface EmailService {
    void sendSimpleMail(EmailRequest emailRequest);

    void sendHtmlMail(EmailRequest emailRequest) throws MessagingException;

    void sendMailWithAttachment(EmailRequest emailRequest);
}
