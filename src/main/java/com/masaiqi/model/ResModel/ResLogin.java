package com.masaiqi.model.ResModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 用户登录响应封装(获取token)
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ResLogin extends BaseResModel {

    /**
     * 用户token信息
     */
    private String token;

}
