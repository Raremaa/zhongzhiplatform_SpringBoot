package com.masaiqi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.User;
import com.masaiqi.model.ReqModel.ReqUserProjejct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 项目表-用户表关联表 Mapper 接口
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Repository
public interface ProjectUserMapper extends BaseMapper<ProjectUser> {

    List<User> getUserByTP(ReqUserProjejct reqUserProjejct);
}
