package com.example.demo.dao.entity.param;

import lombok.Builder;

import java.util.List;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Builder
public class UserConditionBuilder {

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * id的List条件
     */
    private List<Integer> idList;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

}
