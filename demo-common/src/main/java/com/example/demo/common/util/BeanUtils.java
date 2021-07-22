package com.example.demo.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * @author linjian
 * @date 2021/2/16
 */
public class BeanUtils {

    private static final ConcurrentMap<String, BeanCopier> CACHE_COPIER_MAP = Maps.newConcurrentMap();

    /**
     * Copy from sourceList to targetList,return targetList
     *
     * @param sourceList  source list
     * @param targetClass the class of target object
     * @param <T>         the generics of targetClass
     * @return target list
     */
    public static <T> List<T> copyList(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }
        List<T> resultList = Lists.newArrayListWithCapacity(sourceList.size());
        for (Object source : sourceList) {
            try {
                T target = targetClass.newInstance();
                copy(source, target);
                resultList.add(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    /**
     * Copy from source object to target object,return target Object
     *
     * @param source      source object
     * @param targetClass the class of target object
     * @param <T>         the generics of targetClass
     * @return target Object
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        T target;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        copy(source, target);
        return target;
    }

    /**
     * Copy from source object to target object
     *
     * @param source source object
     * @param target target object
     */
    private static void copy(Object source, Object target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        String copierKey = sourceClass.toString() + "#" + targetClass.toString();
        if (CACHE_COPIER_MAP.containsKey(copierKey)) {
            return CACHE_COPIER_MAP.get(copierKey);
        } else {
            BeanCopier beanCopier = BeanCopier.create(sourceClass, targetClass, false);
            CACHE_COPIER_MAP.put(copierKey, beanCopier);
            return beanCopier;
        }
    }
}
