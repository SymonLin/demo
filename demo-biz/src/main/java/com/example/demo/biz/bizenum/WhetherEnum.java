package com.example.demo.biz.bizenum;

import lombok.Getter;

/**
 * @author linjian
 * @date 2021/2/15
 */
public enum WhetherEnum {
    /**
     * 是否
     */
    NO(0, "否"),
    YES(1, "是"),
    ;

    @Getter
    private final Integer key;

    @Getter
    private final String desc;

    WhetherEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
