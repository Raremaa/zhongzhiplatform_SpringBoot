package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.User;
import com.masaiqi.mapper.UserMapper;
import com.masaiqi.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
