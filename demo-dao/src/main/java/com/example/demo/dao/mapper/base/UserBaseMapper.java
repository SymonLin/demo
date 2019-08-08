package com.example.demo.dao.mapper.base;

import com.example.demo.dao.entity.UserDO;
import com.example.demo.dao.entity.param.UserConditionBuilder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linjian
 * @date 2019/8/8
 */
@Repository
public interface UserBaseMapper {

    /**
     * 插入（匹配有值的字段）
     *
     * @param record UserDO
     * @return int
     */
    int insertSelective(UserDO record);

    /**
     * 根据主键ID更新（匹配有值的字段）
     *
     * @param record UserDO
     * @return int
     */
    int updateById(UserDO record);

    /**
     * 动态条件查询（匹配有值的字段）
     *
     * @param params 筛选条件
     * @return List<UserDO>
     */
    List<UserDO> selectByCondition(UserConditionBuilder params);

    /**
     * 根据主键ID查询
     *
     * @param id 主键ID
     * @return UserDO
     */
    UserDO selectById(@Param("id") Integer id);
}