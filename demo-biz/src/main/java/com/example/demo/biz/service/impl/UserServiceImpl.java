package com.example.demo.biz.service.impl;

import com.example.demo.biz.bizenum.WhetherEnum;
import com.example.demo.biz.model.param.UserSaveParam;
import com.example.demo.biz.model.result.UserInfoBO;
import com.example.demo.biz.service.UserService;
import com.example.demo.common.annotation.Cache;
import com.example.demo.common.constant.Constants;
import com.example.demo.common.error.DemoErrors;
import com.example.demo.common.exception.BizException;
import com.example.demo.common.redis.CacheTime;
import com.example.demo.common.util.BeanUtils;
import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.mapper.business.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author linjian
 * @date 2019/2/2
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean saveUser(UserSaveParam param) {
        if (Objects.isNull(param.getId())) {
            return addUser(param);
        } else {
            return updateUser(param);
        }
    }

    @Override
    @Cache(key = "user:%s:info", variables = "#id", expireTime = CacheTime.CACHE_EXP_MINUTE)
    public UserInfoBO getUserInfo(Integer id) {
        UserDO user = this.getUserById(id);
        return BeanUtils.copy(user, UserInfoBO.class);
    }

    private Boolean addUser(UserSaveParam param) {
        UserDO record = BeanUtils.copy(param, UserDO.class);
        userMapper.insertSelective(record);
        return Boolean.TRUE;
    }

    private Boolean updateUser(UserSaveParam param) {
        UserDO user = this.getUserById(param.getId());
        UserDO record = BeanUtils.copy(param, UserDO.class);
        record.setId(user.getId());
        boolean result = userMapper.updateById(record) > Constants.ZERO;
        if (!result) {
            log.error("更新用户失败,param:{}", param);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public UserDO getUserById(Integer id) {
        UserDO user = userMapper.selectById(id);
        if (Objects.isNull(user) || WhetherEnum.YES.getKey().equals(user.getIsDeleted())) {
            log.error("用户不存在或者已删除,id:{}", id);
            throw new BizException(DemoErrors.USER_IS_NOT_EXIST);
        }
        return user;
    }
}
