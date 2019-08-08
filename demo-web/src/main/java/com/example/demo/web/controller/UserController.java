package com.example.demo.web.controller;

import com.example.demo.biz.model.param.UserRegisterParam;
import com.example.demo.biz.service.UserService;
import com.example.demo.common.entity.Result;
import com.example.demo.dao.entity.UserDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjian
 * @date 2019/2/2
 */
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result<Boolean> registerUser(@RequestBody UserRegisterParam param) {
        return Result.wrapSuccessfulResult(userService.registerUser(param));
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{userId}/info")
    public Result<UserDO> getUserInfo(
            @ApiParam(value = "用户ID", example = "1", required = true)
            @PathVariable("userId") Integer userId) {
        return Result.wrapSuccessfulResult(userService.getUserById(userId));
    }
}
