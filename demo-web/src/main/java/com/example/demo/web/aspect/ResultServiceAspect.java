package com.example.demo.web.aspect;

import com.example.demo.biz.exception.BizException;
import com.example.demo.common.entity.Result;
import com.example.demo.common.error.DemoErrors;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author linjian
 * @date 2018/9/26
 */
@Slf4j
@Component
public class ResultServiceAspect implements MethodInterceptor {

    @Override
    public Result invoke(final MethodInvocation methodInvocation) throws Throwable {
        Result result = new Result();
        try {
            String methodName = methodInvocation.getMethod().getName();
            if (log.isDebugEnabled()) {
                log.debug("starting business logic processing.... " + methodName);
            }
            result = (Result) methodInvocation.proceed();
            if (log.isDebugEnabled()) {
                log.debug("finished business logic processing...." + methodName);
            }
        } catch (BizException e) {
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setCode(DemoErrors.PARAM_ERROR.getCode());
            result.setMessage(e.getMessage());
        } catch (RuntimeException e) {
            log.error("系统出错", e);
            result.setSuccess(false);
            result.setCode(DemoErrors.SYSTEM_ERROR.getCode());
            result.setMessage(DemoErrors.SYSTEM_ERROR.getMessage());
        }
        return result;
    }
}
