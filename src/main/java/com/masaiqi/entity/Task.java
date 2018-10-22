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
     * 任务完成情况(由项目负责人裁定)：完成;未完成
     */
    private String status;


}
