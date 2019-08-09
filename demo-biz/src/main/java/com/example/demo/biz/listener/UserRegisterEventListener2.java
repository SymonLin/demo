package com.example.demo.biz.listener;

import com.example.demo.biz.event.UserRegisterEvent;
import com.example.demo.biz.service.EmailService;
import com.example.demo.biz.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Component
public class UserRegisterEventListener2 {

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleUserRegisterEvent(UserRegisterEvent event) {
        smsService.sendSms(event.getMobile());
        emailService.sendEmail(event.getEmailAddress());
    }
}
