package com.example.demo.biz.service.impl;

import com.example.demo.biz.event.UserRegisterEvent;
import com.example.demo.biz.model.param.UserRegisterParam;
import com.example.demo.biz.service.UserService;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.business.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author linjian
 * @date 2019/2/2
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public Boolean registerUser(UserRegisterParam param) {
        long startTime = System.currentTimeMillis();
        Assert.hasText(param.getUserNick(), "用户昵称不能为空");
        Assert.hasText(param.getUserPhone(), "用户手机号不能为空");
        Assert.hasText(param.getUserEmail(), "用户邮箱不能为空");
        userMapper.insertSelective(UserDO.builder()
                .userNick(param.getUserNick())
                .userPhone(param.getUserPhone())
                .userEmail(param.getUserEmail())
                .build());
        publisher.publishEvent(new UserRegisterEvent(this, param.getUserPhone(), param.getUserEmail()));
        log.info("耗时:{}", System.currentTimeMillis() - startTime);
        return true;
    }

    @Override
    public UserDO getUserById(Integer id) {
        Assert.notNull(id, "用户ID不能为空");
        return userMapper.selectById(id);
    }
}
