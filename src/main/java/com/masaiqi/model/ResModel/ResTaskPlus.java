package com.masaiqi.model.ResModel;

import com.masaiqi.entity.Accessory;
import com.masaiqi.entity.Task;
import com.masaiqi.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResTaskPlus extends BaseResModel{
    private Task task;
    private Accessory accessory;
    private Accessory accessoryPerson;
    private User user;
    private String personStatusFormat;
    private String taskName;
    private String userName;
    private String taskUserStatus;
    private String taskStatus;
    private String accessoryUrl;
    private String accessoryPersonUrl;
    private String taskMsg;
    private Integer taskId;
}
