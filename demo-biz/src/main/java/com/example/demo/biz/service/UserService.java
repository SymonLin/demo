package com.example.demo.biz.service;

import com.example.demo.biz.model.param.UserRegisterParam;
import com.example.demo.dao.entity.UserDO;

/**
 * @author linjian
 * @date 2019/2/2
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param param UserRegisterParam
     * @return Boolean
     */
    Boolean registerUser(UserRegisterParam param);

    /**
     * 根据用户ID获取
     *
     * @param id 用户ID
     * @return UserDO
     */
    UserDO getUserById(Integer id);
}
