package com.example.demo.biz.service.impl;

import com.example.demo.biz.service.DemoService;
import com.example.demo.biz.service.UserService;
import com.example.demo.common.redis.CacheTime;
import com.example.demo.common.redis.RedisClient;
import com.example.demo.dao.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author linjian
 * @date 2019/1/15
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    @Override
    public String test(Integer id) {
        Assert.notNull(id, "id不能为空");
        UserDO user = userService.getUserById(id);
        redisClient.set("user:" + id, user, CacheTime.CACHE_EXP_FIVE_MINUTES);
        return user.toString();
    }
}
