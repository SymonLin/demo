package com.example.demo.web.aspect;

import com.example.demo.common.constant.Constants;
import com.example.demo.common.util.CommonUtils;
import com.example.demo.common.util.OperatorUtils;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author linjian
 * @date 2021/2/24
 */
@Slf4j(topic = "controller")
@Aspect
@Component
public class WebLogAspect {

    private static final String LOG_STYLE = "\n"
            // 打印请求 url
            + "URL                : %s\n"
            // 打印 Http method
            + "HTTP Method        : %s\n"
            // 打印来源页面
            + "Referer            : %s\n"
            // 打印客户端信息
            + "Client Info        : %s %s %s %s\n"
            // 用户代理
            + "User Agent         : %s\n"
            // 打印调用 controller 的全路径以及执行方法
            + "Class Method       : %s.%s\n"
            // 打印请求的 IP
            + "IP                 : %s\n"
            // 打印请求入参
            + "Request Args       : %s\n"
            // 打印操作人
            + "Request OperatorId : %s";

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.kylin.*.web.controller..*.*(..))"
            + " && execution(com.kylin.*.common.entity.Result *.*(..))")
    public void webLog() {
        // ignored
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info(String.format(LOG_STYLE,
                request.getRequestURL().toString(),
                request.getMethod(),
                getHeaderParam(request, "referer"),
                getHeaderParam(request, "token"),
                getHeaderParam(request, "appVersion"),
                getHeaderParam(request, "apiVersion"),
                getHeaderParam(request, "deviceId"),
                getHeaderParam(request, "user-agent"),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                CommonUtils.getIpAddr(request),
                new GsonBuilder().disableHtmlEscaping().create().toJson(joinPoint.getArgs()),
                Objects.isNull(OperatorUtils.getOperatorId()) ? Constants.ZERO : OperatorUtils.getOperatorId()
        ));
    }

    private String getHeaderParam(HttpServletRequest request, String paramName) {
        return StringUtils.isEmpty(request.getHeader(paramName)) ? Constants.BLANK : request.getHeader(paramName);
    }
}
