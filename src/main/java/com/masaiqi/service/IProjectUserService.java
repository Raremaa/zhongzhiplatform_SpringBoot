package com.masaiqi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.model.ReqModel.ReqUserProjejct;

import java.util.List;

/**
 * <p>
 * 项目表-用户表关联表 服务类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
public interface IProjectUserService extends IService<ProjectUser> {
    List<User> getUserByTP(ReqUserProjejct reqUserProjejct);
}
