package com.masaiqi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masaiqi.entity.User;
import org.springframework.stereotype.Repository;

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

}
