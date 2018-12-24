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
 * 任务表
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 用户表主键
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 项目表主键
     */
    @TableField("projectId")
    private Integer projectId;

    /**
     * 任务名
     */
    private String name;

    /**
     * 任务的相关描述
     */
    private String msg;

    /**
     * 个人完成情况：已提交；未提交；
     */
    @TableField("userStatus")
    private String userStatus;

    /**
     * 提交的附件在附件表中的Id
     */
    @TableField("accessoryId")
    private Integer accessoryId;

    /**
     * 用户提交的附件
     */
    @TableField("accessoryPersonId")
    private Integer accessoryPersonId;

    /**
     * 个人完成情况: 0-默认 1-准备做 2-已经完成
     */
    @TableField("personStatus")
    private Integer personStatus;
    /**
     * 任务完成情况(由项目负责人裁定)：完成;未完成
     */
    private String status;

    @TableField(exist = false)
    private String time;

    @TableField(exist = false)
    private String checkTime;

    @TableField(exist = false)
    private String accessoryUrl;

    @TableField(exist = false)
    private String accessoryPersonUrl;
}
