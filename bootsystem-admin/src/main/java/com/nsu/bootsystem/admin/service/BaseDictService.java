package com.nsu.bootsystem.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nsu.common.utils.PageUtils;
import com.nsu.bootsystem.admin.entity.BaseDictEntity;

import java.util.Map;

/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:11
 */
public interface BaseDictService extends IService<BaseDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

