package com.example.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjian
 * @date 2019/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 用户昵称
     */
    private String userNick;

}
