package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.Accessory;
import com.masaiqi.entity.Task;
import com.masaiqi.mapper.AccessoryMapper;
import com.masaiqi.mapper.TaskMapper;
import com.masaiqi.model.ReqModel.ReqTask;
import com.masaiqi.model.ResModel.ResTask;
import com.masaiqi.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Autowired
    AccessoryMapper accessoryMapper;

    @Override
    public List<ResTask> getTask(Task task) {
        return baseMapper.getTask(task);
    }

    @Override
    public void addTask(ReqTask reqTask) {
        Accessory accessory = reqTask.getAccessory();
        Task task = reqTask.getTask();
        accessoryMapper.insert(accessory);
        task.setAccessoryId(accessory.getId());
        baseMapper.insert(task);
    }

}
