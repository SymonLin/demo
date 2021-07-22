package com.example.demo.common.redis;

/**
 * @author linjian
 * @date 2019/3/2
 */
public class CacheTime {

    /**
     * 缓存时效 5秒钟
     */
    public static final int CACHE_EXP_FIVE_SECONDS = 5;

    /**
     * 缓存时效 1分钟
     */
    public static final int CACHE_EXP_MINUTE = 60;

    /**
     * 缓存时效 5分钟
     */
    public static final int CACHE_EXP_FIVE_MINUTES = 60 * 5;

    /**
     * 缓存时效 10分钟
     */
    public static final int CACHE_EXP_TEN_MINUTES = 60 * 10;

    /**
     * 缓存时效 15分钟
     */
    public static final int CACHE_EXP_QUARTER_MINUTES = 60 * 15;

    /**
     * 缓存时效 60分钟
     */
    public static final int CACHE_EXP_HOUR = 60 * 60;

    /**
     * 缓存时效 12小时
     */
    public static final int CACHE_EXP_HALF_DAY = 12 * 60 * 60;

    /**
     * 缓存时效 1天
     */
    public static final int CACHE_EXP_DAY = 3600 * 24;

    /**
     * 缓存时效 1周
     */
    public static final int CACHE_EXP_WEEK = 3600 * 24 * 7;

    /**
     * 缓存时效 1月
     */
    public static final int CACHE_EXP_MONTH = 3600 * 24 * 30;

    /**
     * 缓存时效 永久
     */
    public static final int CACHE_EXP_FOREVER = 0;
}
