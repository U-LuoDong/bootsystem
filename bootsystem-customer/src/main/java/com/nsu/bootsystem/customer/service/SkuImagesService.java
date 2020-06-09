package com.nsu.bootsystem.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nsu.common.utils.PageUtils;
import com.nsu.bootsystem.customer.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

