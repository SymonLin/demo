package com.example.demo.biz.service;

/**
 * @author linjian
 * @date 2019/2/2
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param userNick 用户昵称
     * @return Boolean
     */
    Boolean addUser(String userNick);

    /**
     * 获取用户字符串
     *
     * @param id 用户ID
     * @return String
     */
    String getUserStr(Integer id);
}
