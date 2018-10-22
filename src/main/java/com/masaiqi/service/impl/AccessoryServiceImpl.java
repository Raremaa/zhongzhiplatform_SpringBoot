package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.Accessory;
import com.masaiqi.mapper.AccessoryMapper;
import com.masaiqi.service.IAccessoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class AccessoryServiceImpl extends ServiceImpl<AccessoryMapper, Accessory> implements IAccessoryService {

}
