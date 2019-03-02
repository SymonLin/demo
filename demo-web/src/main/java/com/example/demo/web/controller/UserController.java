package com.example.demo.web.controller;

import com.example.demo.biz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("add")
    @ApiOperation("添加用户")
    public void addUser(
            @ApiParam(name = "userNick", value = "用户昵称", example = "test", required = true)
            @RequestParam("userNick") String userNick) {
        userService.addUser(userNick);
    }
}
