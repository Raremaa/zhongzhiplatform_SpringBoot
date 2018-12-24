package com.masaiqi.model.ReqModel;

import com.masaiqi.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqUserProjejct extends BaseReqModel{

    private Integer teamId;
    private Integer projectId;
}
