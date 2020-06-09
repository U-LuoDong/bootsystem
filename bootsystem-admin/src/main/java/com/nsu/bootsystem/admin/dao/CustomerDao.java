package com.nsu.bootsystem.admin.dao;

import com.nsu.bootsystem.admin.entity.CustomerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:11
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {
	
}
