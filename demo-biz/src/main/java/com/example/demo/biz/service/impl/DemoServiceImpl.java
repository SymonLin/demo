package com.example.demo.biz.service.impl;

import com.example.demo.biz.service.DemoService;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.business.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjian
 * @date 2019/1/15
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String test() {
        UserDO user = userMapper.selectById(1);
        return user.toString();
    }
}
