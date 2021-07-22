package com.example.demo.biz.service;

import com.example.demo.biz.model.param.UserSaveParam;
import com.example.demo.biz.model.result.UserInfoBO;
import com.example.demo.dao.entity.UserDO;

/**
 * @author linjian
 * @date 2019/2/2
 */
public interface UserService {

    /**
     * 保存用户
     *
     * @param param UserSaveParam
     * @return Boolean
     */
    Boolean saveUser(UserSaveParam param);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return UserInfoBO
     */
    UserInfoBO getUserInfo(Integer id);

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return UserDO
     */
    UserDO getUserById(Integer id);
}
