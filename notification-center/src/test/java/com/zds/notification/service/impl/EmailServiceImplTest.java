package com.zds.notification.service.impl;

import com.zds.notification.properties.EmailProperties;
import com.zds.notification.service.EmailService;
import com.zds.vo.request.EmailRequest;
import javax.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * todo:description
 *
 * @author zhongdongsheng
 * @since 2022/7/19
 */
@SpringBootTest
class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailProperties emailProperties;

    @Test
    void sendSimpleMail() {
        EmailRequest emailRequest = EmailRequest.builder()
            .sendFrom(emailProperties.getSendFrom())
            .sendTo(emailProperties.getSendTo())
            .content("Hello, This is a simple Email!")
            .subject("简单邮件测试")
        .build();
        emailService.sendSimpleMail(emailRequest);
    }

    @Test
    void sendHtmlMail() throws MessagingException {
        EmailRequest emailRequest = EmailRequest.builder()
            .sendFrom(emailProperties.getSendFrom())
            .sendTo(emailProperties.getSendTo())
            .content("Hello, This is a Html Email!")
            .subject("HTML模板邮件测试")
            .templateName("notice")
            .build();
        emailService.sendHtmlMail(emailRequest);
    }

    @Test
    void sendMailWithAttachment() {
    }
}