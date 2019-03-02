package com.example.demo.dao.entity.param;

import lombok.Builder;

import java.util.List;

/**
 * @author linjian
 * @date 2019/1/15
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

}
