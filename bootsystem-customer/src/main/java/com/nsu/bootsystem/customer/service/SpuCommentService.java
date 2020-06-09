package com.nsu.bootsystem.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nsu.common.utils.PageUtils;
import com.nsu.bootsystem.customer.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

