package com.example.demo.web.aspect;

import com.example.demo.common.annotation.Cache;
import com.example.demo.common.redis.RedisClient;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author linjian
 * @date 2019/3/19
 */
@Slf4j
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private RedisClient redisClient;

    @Around("@annotation(cache)")
    public Object doAround(ProceedingJoinPoint joinPoint, Cache cache) throws Throwable {
        String cacheKey = this.handleCacheKey(joinPoint, cache);
        Object data = redisClient.get(cacheKey);
        if (Objects.nonNull(data)) {
            return data;
        }
        data = joinPoint.proceed();
        boolean result = redisClient.set(cacheKey, data, cache.expireTime());
        if (result) {
            log.info("将数据缓存到Redis,cacheKey:{}", cacheKey);
        }
        return data;
    }

    private String handleCacheKey(ProceedingJoinPoint joinPoint, Cache cache) {
        String cacheKey = cache.key();
        List<String> variableList = Arrays.asList(cache.variables());
        if (CollectionUtils.isEmpty(variableList)) {
            return cacheKey;
        }
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] params = discoverer.getParameterNames(signature.getMethod());
        if (Objects.isNull(params)) {
            params = new String[]{};
        }
        // 把方法参数放入SpEL上下文中
        StandardEvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        // 使用SpEL解析key
        ExpressionParser parser = new SpelExpressionParser();
        List<String> valueList = Lists.newArrayList();
        for (String variable : variableList) {
            valueList.add(parser.parseExpression(variable).getValue(context, String.class));
        }
        return String.format(cacheKey, valueList.toArray());
    }
}
