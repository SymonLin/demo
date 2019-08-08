package com.example.demo.biz.service;

/**
 * @author linjian
 * @date 2019/8/8
 */
public interface SmsService {

    /**
     * 发短信
     *
     * @param mobile 手机号
     */
    void sendSms(String mobile);
}
