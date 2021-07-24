package com.example.demo.common.util;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author linjian
 * @date 2021/2/24
 */
public class OperatorUtils {

    private static FastThreadLocal<Integer> operator = new FastThreadLocal<>();

    public static void setOperatorId(Integer operatorId) {
        operator.set(operatorId);
    }

    public static void clearOperatorId() {
        operator.remove();
    }

    public static Integer getOperatorId() {
        return operator.get();
    }
}
