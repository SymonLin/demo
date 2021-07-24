package com.example.demo.web.aspect;

import com.example.demo.common.entity.Result;
import com.example.demo.common.error.DemoErrors;
import com.example.demo.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

/**
 * @author linjian
 * @date 2019/3/19
 */
@Slf4j
@Aspect
@Component
public class ControllerResultAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)" +
            "&& execution(com.example.demo.common.entity.Result *.*(..))")
    public void controllerResult() {
    }

    @Around("controllerResult()")
    public Result<?> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result<?> result = new Result<>();
        try {
            result = (Result<?>) proceedingJoinPoint.proceed();
        } catch (BizException e) {
            log.error("业务异常 " + Arrays.toString(proceedingJoinPoint.getArgs()), e);
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            result.setSuccess(false);
            result.setCode(DemoErrors.PARAM_ERROR.getCode());
            result.setMessage(e.getMessage());
        } catch (ConstraintViolationException e) {
            result.setSuccess(false);
            result.setCode(DemoErrors.PARAM_ERROR.getCode());
            result.setMessage(e.getMessage().substring(e.getMessage().indexOf(":") + 2));
        } catch (RuntimeException e) {
            log.error("系统出错 " + Arrays.toString(proceedingJoinPoint.getArgs()), e);
            result.setSuccess(false);
            result.setCode(DemoErrors.SYSTEM_ERROR.getCode());
            result.setMessage(DemoErrors.SYSTEM_ERROR.getMessage());
        }
        return result;
    }
}
