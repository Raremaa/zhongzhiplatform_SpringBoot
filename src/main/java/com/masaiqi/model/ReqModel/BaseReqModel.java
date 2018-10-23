package com.masaiqi.model.ReqModel;

import lombok.Builder;
import lombok.Data;

/**
 * 抽离请求参数模型
 * 方便参数处理 加强安全性
 * 原实体字段可以加上@JsonIgnore 进行序列化忽略
 */
@Data
public class BaseReqModel {

    private Integer id;//表主键
    private String condition;//复杂条件拼装

}
