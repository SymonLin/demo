package com.example.demo.dao.entity.param;

import lombok.Builder;

import java.util.List;

/**
 * @author linjian
 * @date 2021/7/22
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
     * 是否删除(0:未删除 1:已删除)
     */
    private Integer isDeleted;

    /**
     * 记录创建时间
     */
    private java.time.LocalDateTime createTime;

    /**
     * 记录修改时间
     */
    private java.time.LocalDateTime modifyTime;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户性别(1:男 2:女)
     */
    private Integer userSex;

    /**
     * userSex的List条件
     */
    private List<Integer> userSexList;

    /**
     * 出生日期
     */
    private java.time.LocalDate userBirthday;

    /**
     * 用户头像
     */
    private String userAvatar;

}
