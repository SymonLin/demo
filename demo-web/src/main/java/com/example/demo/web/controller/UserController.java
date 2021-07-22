package com.example.demo.web.controller;

import com.example.demo.biz.model.param.UserSaveParam;
import com.example.demo.biz.model.result.UserInfoBO;
import com.example.demo.biz.service.UserService;
import com.example.demo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author linjian
 * @date 2019/2/2
 */
@Api(tags = "用户")
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("保存用户")
    @PostMapping("/save")
    public Result<Boolean> saveUser(@RequestBody @Validated UserSaveParam param) {
        return Result.wrapSuccessfulResult(userService.saveUser(param));
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result<UserInfoBO> getUserInfo(
            @ApiParam(name = "id", value = "用户ID", example = "1", required = true)
            @RequestParam("id") @NotNull(message = "用户ID不能为空") Integer id) {
        return Result.wrapSuccessfulResult(userService.getUserInfo(id));
    }
}
