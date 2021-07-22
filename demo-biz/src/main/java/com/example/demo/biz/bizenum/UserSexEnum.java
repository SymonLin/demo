package com.example.demo.biz.bizenum;

import lombok.Getter;

/**
 * @author linjian
 * @date 2021/2/18
 */
public enum UserSexEnum {
    /**
     * 用户性别
     */
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    @Getter
    private final Integer sex;

    @Getter
    private final String desc;

    UserSexEnum(Integer sex, String desc) {
        this.sex = sex;
        this.desc = desc;
    }

    public static boolean isValid(Integer sex) {
        for (UserSexEnum sexEnum : values()) {
            if (sexEnum.sex.equals(sex)) {
                return true;
            }
        }
        return false;
    }
}
