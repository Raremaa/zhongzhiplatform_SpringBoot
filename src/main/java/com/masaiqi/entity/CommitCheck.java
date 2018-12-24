package com.masaiqi.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * @author masaiqi
 * @since 2018-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommitCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    private LocalDate time;

    @TableField("taskStatus")
    private String taskStatus;

    @TableField("isCheck")
    private Integer isCheck;

    private String msg;

    @TableField("checkTime")
    private LocalDate checkTime;

    @TableField("taskId")
    private Integer taskId;

    @TableField(exist = false)
    private String timeFormat;

    @TableField(exist = false)
    private String checkTimeFormat;

    @TableField(exist = false)
    private String isCheckFormat;

    @TableField("accessoryId")
    private Integer accessoryId;

    @TableField(exist = false)
    private String accessoryUrl;

    @TableField(exist = false)
    private Accessory accessory;

    public String getTimeFormat() {
        if(this.time != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日");
            return this.time.format(formatter);
        }else{
            return this.timeFormat;
        }
    }

    public String getCheckTimeFormat() {
        if(this.checkTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日");
            return this.checkTime.format(formatter);
        }else {
            return this.checkTimeFormat;
        }
    }

    public String getIsCheckFormat() {
        if(this.isCheck == 0){
            return "未审核";
        }else {
            return "已审核";
        }
    }
}
