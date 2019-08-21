package com.example.demo.biz.service.impl;

import com.example.demo.biz.exception.BizException;
import com.example.demo.biz.service.DemoService;
import com.example.demo.common.error.DemoErrors;
import com.example.demo.common.redis.CacheTime;
import com.example.demo.common.redis.RedisClient;
import com.example.demo.common.util.HttpUtils;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.business.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author linjian
 * @date 2019/1/15
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public String test(Integer id) {
        Assert.notNull(id, "id不能为空");
        UserDO user = userMapper.selectById(id);
        if (Objects.isNull(user)) {
            throw new BizException(DemoErrors.USER_IS_NOT_EXIST);
        }
        redisClient.set("user:" + id, user, CacheTime.CACHE_EXP_FIVE_MINUTES);
        return user.toString();
    }

    @Override
    public void testHttp() {
        long start = System.currentTimeMillis();
        String json = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/user/info", "access_token=24__SkSX4SoP7eSaIe1uNjNNqvp9SBcryXj2f4MxOnqi7map2eaNQyE50JzUdGJb7aye1d4ZkgsEJKoVnph-oiXVHPvNERC2QV3K2Rb7dCz8sdiBo9s7487DAarTPaneCY8XzY6UIXncKKFTsmYLQVgAAAQVA&openid=o7G4BwcOEJMCAqJ0yByxYNJJU2H4&lang=zh_CN");
        log.info("耗时:{}", System.currentTimeMillis() - start);
        log.info(json);
    }
}
