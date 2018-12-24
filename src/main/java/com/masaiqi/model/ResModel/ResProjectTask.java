package com.masaiqi.model.ResModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResProjectTask extends BaseResModel{

    /**
     * 封装该任务组全部任务
     */
    private List<ResTask> list1;

    /**
     * 封装该用户所属全部任务
     */
    private List<ResTask> list2;

    /**
     * 封装该用户准备做的任务
     */
    private List<ResTask> list3;

    /**
     * 封装该用户已经完成的任务
     */
    private List<ResTask> list4;

}
