package com.example.demo.biz.model.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author linjian
 * @date 2019/2/28
 */
@Data
@ApiModel(description = "用户信息")
public class UserInfoBO {

    @ApiModelProperty(value = "用户ID", example = "1")
    private Integer id;

    @ApiModelProperty(value = "手机号", example = "13888888888")
    private String userPhone;

    @ApiModelProperty(value = "用户姓名", example = "张三")
    private String userName;

    @ApiModelProperty(value = "用户性别(1:男 2:女)", example = "1")
    private Integer userSex;

    @ApiModelProperty(value = "出生日期", example = "1970-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirthday;

    @ApiModelProperty(value = "用户头像", example = "avatar.png")
    private String userAvatar;
}
