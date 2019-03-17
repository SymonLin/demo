package com.example.demo.common.error;

/**
 * @author linjian
 * @date 2019/3/14
 */
public enum DemoErrors implements ServiceErrors {
    /**
     * 错误码
     */
    SYSTEM_ERROR(10000, "系统错误"),
    PARAM_ERROR(10001, "参数错误"),

    USER_IS_NOT_EXIST(20000, "用户不存在"),
    ;
    private Integer code;

    private String message;

    DemoErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
