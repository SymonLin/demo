package com.example.demo.web.aspect;

import com.example.demo.biz.exception.BizException;
import com.example.demo.common.entity.Result;
import com.example.demo.common.error.DemoErrors;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author linjian
 * @date 2019/3/14
 */
@Slf4j
@Component
public class DubboServiceAspect implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } catch (BizException e) {
            log.error("BizException", e);
            return exceptionProcessor(methodInvocation, e);
        } catch (Exception e) {
            log.error("Exception:", e);
            return exceptionProcessor(methodInvocation, e);
        }
    }

    private Object exceptionProcessor(MethodInvocation methodInvocation, Exception e) {
        Object[] args = methodInvocation.getArguments();
        Method method = methodInvocation.getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        log.error("dubbo服务[method=" + methodName + "] params=" + Arrays.toString(args) + "异常：", e);
        Class<?> clazz = method.getReturnType();
        if (clazz.equals(Result.class)) {
            Result result = new Result();
            result.setSuccess(false);
            if (e instanceof BizException) {
                result.setCode(((BizException) e).getCode());
                result.setMessage(e.getMessage());
            } else {
                result.setCode(DemoErrors.SYSTEM_ERROR.getCode());
                result.setMessage(DemoErrors.SYSTEM_ERROR.getMessage());
            }
            return result;
        }
        return null;
    }
}
