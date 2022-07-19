package com.zds.notification.service.impl;

import com.zds.notification.service.EmailService;
import com.zds.vo.request.EmailRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public void sendSimpleMail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(emailRequest.getSubject());
        message.setFrom(emailRequest.getSendFrom());
        message.setTo(emailRequest.getSendTo());
        String nowTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        message.setSentDate(new Date());
        message.setText(emailRequest.getContent() + nowTime);

        mailSender.send(message);
    }

    @Override
    public void sendHtmlMail(EmailRequest emailRequest) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        Context context = new Context();
        context.setVariable("name", "zhongdongsheng");
        context.setVariable("content", emailRequest.getContent());
        context.setVariable("nowTime", LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String htmlContent = templateEngine.process(emailRequest.getTemplateName(), context);
        mimeMessageHelper.setFrom(emailRequest.getSendFrom());
        mimeMessageHelper.setTo(emailRequest.getSendTo());
        mimeMessageHelper.setText(htmlContent, true);
        mimeMessageHelper.setSubject(emailRequest.getSubject());
        mimeMessageHelper.setSentDate(new Date());

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendMailWithAttachment(EmailRequest emailRequest) {

    }
}
