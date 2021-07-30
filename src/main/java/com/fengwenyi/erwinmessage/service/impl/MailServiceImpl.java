package com.fengwenyi.erwinmessage.service.impl;

import com.fengwenyi.erwinmessage.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-31
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    private final MailProperties mailProperties;

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(MailProperties mailProperties, JavaMailSender javaMailSender) {
        this.mailProperties = mailProperties;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendText(String receiver, String title, String content) {

        String username = mailProperties.getUsername();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(receiver);
        message.setSubject(title);
        message.setText(content);
        try {
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("发送简单邮件时发生异常！", e);
        }
        return false;
    }
}
