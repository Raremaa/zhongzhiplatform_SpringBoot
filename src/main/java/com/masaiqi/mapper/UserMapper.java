package com.masaiqi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masaiqi.entity.User;
import com.masaiqi.model.ResModel.ResUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 更具team表主键查询用户信息
     * @param teamId team表主键
     * @return 用户信息
     */
    List<ResUser> getUserByTeamId(@Param("teamId") Integer teamId);
}
