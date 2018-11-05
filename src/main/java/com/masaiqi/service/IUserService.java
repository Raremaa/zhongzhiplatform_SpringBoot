package com.masaiqi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.masaiqi.entity.User;
import com.masaiqi.model.ReqModel.ReqUser;
import com.masaiqi.model.ResModel.ResUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
public interface IUserService extends IService<User> {
    boolean register(ReqUser reqUser);
    List<ResUser> getUserByTeamId(Integer teamId);
}
