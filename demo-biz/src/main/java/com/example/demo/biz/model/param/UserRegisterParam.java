package com.example.demo.biz.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Data
@ApiModel("用户注册入参")
public class UserRegisterParam {

    @ApiModelProperty(value = "用户昵称", example = "半夜睡地板", required = true)
    private String userNick;

    @ApiModelProperty(value = "用户手机号", example = "13812345678", required = true)
    private String userPhone;

    @ApiModelProperty(value = "用户邮箱", example = "1000@qq.com", required = true)
    private String userEmail;
}
