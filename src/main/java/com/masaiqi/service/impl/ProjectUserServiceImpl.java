package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.mapper.ProjectUserMapper;
import com.masaiqi.model.ReqModel.ReqUserProjejct;
import com.masaiqi.service.IProjectUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 项目表-用户表关联表 服务实现类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class ProjectUserServiceImpl extends ServiceImpl<ProjectUserMapper, ProjectUser> implements IProjectUserService {

    @Override
    public List<User> getUserByTP(ReqUserProjejct reqUserProjejct) {
        return baseMapper.getUserByTP(reqUserProjejct);
    }
}
