package com.masaiqi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 项目负责人
     */
    @TableField("projectLeader")
    private Integer projectLeader;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 完成情况：完成；项目流产；已逾期
     */
    private String status;

    /**
     * 规定完成时间
     */
    private LocalDate deadtime;

    @TableField(exist = false)
    private String deadtimeFormat;

    /**
     * 开始时间
     */
    private LocalDate starttime;

    @TableField(exist = false)
    private String starttimeFormat;

    /**
     * 所属团队Id
     */
    @TableField("teamId")
    private Integer teamId;

    /**
     * 项目表Id
     */
    @TableField("accessoryId")
    private Integer accessoryId;

    @TableField(exist = false)
    private String projectLeaderName;

    public String getDeadtimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日");
        return this.deadtime.format(formatter);
    }

    public String getStarttimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日");
        return this.starttime.format(formatter);
    }
}
