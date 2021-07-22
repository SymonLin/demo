package com.example.demo.biz.model.param;

import com.example.demo.biz.bizenum.UserSexEnum;
import com.example.demo.common.annotation.EnumValid;
import com.example.demo.common.validation.group.VerifySequence;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author linjian
 * @date 2021/2/16
 */
@Data
@ApiModel(description = "保存用户参数")
public class UserSaveParam {

    @ApiModelProperty(value = "用户ID", example = "1", hidden = true)
    private Integer id;

    @NotBlank(message = "用户姓名不能为空")
    @ApiModelProperty(value = "用户姓名", example = "张三", required = true)
    private String userName;

    @EnumValid(enumClass = UserSexEnum.class, enumMethodName = "isValid", message = "用户性别无效",
            groups = VerifySequence.VerifyEnum.class)
    @ApiModelProperty(value = "用户性别(1:男 2:女)", example = "1", required = true)
    private Integer userSex;

    @NotNull(message = "出生日期不能为空")
    @ApiModelProperty(value = "出生日期", example = "1970-01-01", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirthday;

    @ApiModelProperty(value = "用户头像", example = "avatar.png")
    private String userAvatar;

    @Email(message = "邮箱格式有误")
    @ApiModelProperty(value = "电子邮箱", example = "a@qq.com")
    private String userEmail;
}
