package com.example.demo.biz.service;

/**
 * @author linjian
 * @date 2019/8/8
 */
public interface EmailService {

    /**
     * 发邮件
     *
     * @param emailAddress 邮箱地址
     */
    void sendEmail(String emailAddress);
}
