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
 * 团队表
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;


    /**
     * 团队名称
     */
    private String name;

    /**
     * 团队头像url路径
     */
    @TableField("pictureUrl")
    private String pictureUrl;

    /**
     * 团队介绍
     */
    private String introduction;

    /**
     * 团队所有者
     */
    @TableField("Leader")
    private Integer Leader;


}
