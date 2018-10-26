package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.Team;
import com.masaiqi.entity.User;
import com.masaiqi.mapper.TeamMapper;
import com.masaiqi.mapper.UserMapper;
import com.masaiqi.model.ReqModel.ReqUser;
import com.masaiqi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TeamMapper teamMapper;

    /**、
     * 注册
     * @param reqUser
     * @return
     */
    @Override
    public boolean register(ReqUser reqUser) {
        //保存至用户表
        User user = new User();
        user.setName(reqUser.getName());
        user.setNo(reqUser.getNo());
        user.setPassword(reqUser.getPassword());
        user.setPhone(reqUser.getPhone());
        user.setEmail(reqUser.getEmail());
        user.setAuthority(1);//团队拥有者
        baseMapper.insert(user);
        User userTemp = baseMapper.selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getPhone,user.getPhone())
                .eq(User::getPassword,user.getPassword())
        );
        //保存至团队表
        Team team = new Team();
        team.setLeader(userTemp.getId());
        team.setName(reqUser.getTeamName());
        teamMapper.insert(team);
        return true;
    }

}
