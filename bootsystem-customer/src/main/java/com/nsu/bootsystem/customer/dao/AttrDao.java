package com.nsu.bootsystem.customer.dao;

import com.nsu.bootsystem.customer.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
