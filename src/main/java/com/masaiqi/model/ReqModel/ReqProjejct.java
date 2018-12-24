package com.masaiqi.model.ReqModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqProjejct extends BaseReqModel{

    private Integer projectId;//任务组编号
    private Integer userId;//用户编号
}
