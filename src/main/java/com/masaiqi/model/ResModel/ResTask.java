package com.masaiqi.model.ResModel;

import com.masaiqi.entity.Accessory;
import com.masaiqi.entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResTask extends BaseResModel{

    private Integer Id;

    /**
     * 用户表主键
     */
    private Integer userId;

    /**
     * 项目表主键
     */
    private Integer projectId;

    /**
     * 任务名
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 任务的相关描述
     */
    private String msg;


    /**
     * 个人完成情况: 0-默认 1-准备做 2-已经完成
     */
    private Integer personStatus;

    /**
     * 个人完成情况：已提交；未提交；
     */
    private String userStatus;

    /**
     * 提交的附件在附件表中的Id
     */
    private Integer accessoryId;

    private Integer accessoryPersonId;

    private Accessory accessoryPerson;
    /**
     * 对应的附件信息
     */
    private Accessory accessory;

    /**
     * 任务完成情况(由项目负责人裁定)：完成;未完成
     */
    private String status;

    /**
     * 个人完成情况
     */
    private String personStatusFormat;

    /**
     * 前端list集合标志 1-list1 2-list2 3-list3 4-list4
     */
    private Integer flag;

    public String getPersonStatusFormat() {
        if(this.personStatus.equals(0)){
            this.personStatusFormat = "default";
        }
        if(this.personStatus.equals(1)){
            this.personStatusFormat = "准备做";
        }
        if(this.personStatus.equals(2)){
            this.personStatusFormat = "已完成";
        }
        return personStatusFormat;
    }

    public void setTaskData(Task taskData){
        this.Id = taskData.getId();
        this.userId = taskData.getUserId();
        this.projectId = taskData.getProjectId();
        this.name = taskData.getName();
        this.msg = taskData.getMsg();
        this.userStatus = taskData.getUserStatus();
        this.accessoryId = taskData.getAccessoryId();
        this.status = taskData.getStatus();
        this.personStatus = taskData.getPersonStatus();
    }
}
