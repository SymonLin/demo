package com.example.demo.biz.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author linjian
 * @date 2019/8/8
 */
public class UserRegisterEvent extends ApplicationEvent {

    private static final long serialVersionUID = 8980725721131427952L;

    private String mobile;

    private String emailAddress;

    public UserRegisterEvent(Object source, String mobile, String emailAddress) {
        super(source);
        this.mobile = mobile;
        this.emailAddress = emailAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
