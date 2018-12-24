package com.masaiqi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.masaiqi.entity.Accessory;
import com.masaiqi.entity.Notice;
import com.masaiqi.mapper.AccessoryMapper;
import com.masaiqi.mapper.NoticeMapper;
import com.masaiqi.service.IAccessoryService;
import com.masaiqi.service.INoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author masaiqi
 * @since 2018-10-22
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
