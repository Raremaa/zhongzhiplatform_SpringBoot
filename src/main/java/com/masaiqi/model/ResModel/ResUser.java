package com.masaiqi.model.ResModel;

import com.masaiqi.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ResUser extends BaseResModel {

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 用户编号
     */
    private String no;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 权限 admin project_leader team_leader
     */
    private String access;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 用户头像url路径
     */
    private String pictureUrl;

    /**
     * 用户个人介绍
     */
    private String introduction;

    /**
     * 所属团队Id
     */
    private Integer teamId;

    /**
     * 用户token信息
     */
    private String token;

    /**
     * 所属项目编号
     */
    private List<Integer> projectId;
}
