package com.masaiqi.model.ResModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 用户信息响应封装
 */
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
