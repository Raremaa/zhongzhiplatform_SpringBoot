package com.masaiqi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.masaiqi.entity.Task;
import com.masaiqi.model.ReqModel.ReqTask;
import com.masaiqi.model.ResModel.ResTask;

import java.util.List;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
public interface ITaskService extends IService<Task> {
    List<ResTask> getTask(Task task);
    void addTask (ReqTask reqTask);
}
