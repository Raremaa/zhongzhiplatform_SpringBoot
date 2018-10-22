package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.Team;
import com.masaiqi.mapper.TeamMapper;
import com.masaiqi.service.ITeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 团队表 服务实现类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements ITeamService {

}
