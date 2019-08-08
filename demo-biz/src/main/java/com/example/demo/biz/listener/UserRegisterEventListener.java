package com.example.demo.biz.listener;

import com.example.demo.biz.event.UserRegisterEvent;
import com.example.demo.biz.service.EmailService;
import com.example.demo.biz.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Component
public class UserRegisterEventListener implements ApplicationListener<UserRegisterEvent> {

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        smsService.sendSms(event.getMobile());
        emailService.sendEmail(event.getEmailAddress());
    }
}
