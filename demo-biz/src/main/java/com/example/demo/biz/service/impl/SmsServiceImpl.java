package com.example.demo.biz.service.impl;

import com.example.demo.biz.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Async
    @Override
    public void sendSms(String mobile) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("向{}发送短信", mobile);
    }
}
