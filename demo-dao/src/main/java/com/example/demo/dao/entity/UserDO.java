package com.example.demo.dao.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
     * 用户名
     */
    private String userName;

}
