package com.example.demo.biz.service.impl;

import com.example.demo.biz.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Async
    @Override
    public void sendEmail(String emailAddress) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("向{}发送邮件", emailAddress);
    }
}
