package com.masaiqi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.masaiqi.entity.User;
import com.masaiqi.model.ReqModel.ReqUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
public interface IUserService extends IService<User> {
    public boolean register(ReqUser reqUser);
}