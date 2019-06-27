package com.example.demo.biz.service.impl;

import com.example.demo.biz.exception.BizException;
import com.example.demo.biz.service.DemoService;
import com.example.demo.biz.service.UserService;
import com.example.demo.common.error.DemoErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linjian
 * @date 2019/1/15
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserService userService;

    @Override
    public String test(Integer id) {
        return userService.getUserStr(id);
    }

    @Override
    public void testTransaction() {
        innerMethod();
    }

    @Transactional(rollbackFor = Exception.class)
    private void innerMethod() {
        userService.addUser("张三");
        userService.addUser("李四");
        throw new BizException(DemoErrors.SYSTEM_ERROR);
    }
}
