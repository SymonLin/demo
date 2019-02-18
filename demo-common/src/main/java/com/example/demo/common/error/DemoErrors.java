package com.example.demo.common.error;


/**
 * @author linjian
 * @date 2019/2/18
 */
public enum DemoErrors {
    /**
     * 业务错误码
     */
    USER_IS_NOT_EXIST(20000, "用户不存在或已删除"),
    ;
    private Integer code;

    private String message;

    DemoErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
