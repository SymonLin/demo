package com.example.demo.common.error;

/**
 * @author linjian
 * @date 2019/3/14
 */
public interface ServiceErrors {

    /**
     * 获取错误码
     *
     * @return Integer
     */
    Integer getCode();

    /**
     * 获取错误信息
     *
     * @return String
     */
    String getMessage();
}
