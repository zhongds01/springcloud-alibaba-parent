package com.zds.notification.service.impl;

import com.zds.notification.service.EmailService;
import com.zds.vo.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * EmailServiceImpl
 *
 * @author zhongdongsheng
 * @since 2022/7/18
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendHtmlMail(EmailRequest emailRequest) {
        Context context = new Context();
        // 暂时写死
        templateEngine.process("notice.html", context);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject(emailRequest.getSubject());
    }
}
