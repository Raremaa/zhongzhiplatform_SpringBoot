package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.Project;
import com.masaiqi.mapper.ProjectMapper;
import com.masaiqi.service.IProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
