package com.masaiqi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

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
     * 权限:0-管理员；1-团队拥有者；2-团队成员
     */
    private Integer authority;

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
    @TableField("pictureUrl")
    private String pictureUrl;

    /**
     * 用户个人介绍
     */
    private String introduction;

    /**
     * 所属团队Id
     */
    @TableField("teamId")
    private Integer teamId;
}
