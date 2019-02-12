package com.example.demo.biz.service.impl;

import com.example.demo.biz.service.UserService;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.business.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjian
 * @date 2019/2/2
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addUser(String userName) {
        UserDO user = UserDO.builder()
                .userName(userName)
                .build();
        return userMapper.insertSelective(user) > 0;
    }
}
