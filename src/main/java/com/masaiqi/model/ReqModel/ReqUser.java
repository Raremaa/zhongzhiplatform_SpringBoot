package com.masaiqi.model.ReqModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ReqUser extends BaseReqModel {

    /**
     * 用户表主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    private String no;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * token信息
     */
    private String token;

    /**
     * 团队名
     */
    private String teamName;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 用户权限
     * 0-管理员；1-团队拥有者；2-团队成员
     */
    private Integer authority;

    /**
     * 团队表主键
     */
    private Integer teamId;
}
