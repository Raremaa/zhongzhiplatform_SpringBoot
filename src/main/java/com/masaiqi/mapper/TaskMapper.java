package com.masaiqi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.masaiqi.entity.Task;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Repository
public interface TaskMapper extends BaseMapper<Task> {

}
