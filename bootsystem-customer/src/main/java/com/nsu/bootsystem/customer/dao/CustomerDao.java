package com.nsu.bootsystem.customer.dao;

import com.nsu.bootsystem.customer.entity.CustomerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * 
 * 
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-10 17:51:35
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {

    ArrayList<String> queryCustSource();

    ArrayList<String> queryCustIndustry();

    CustomerEntity queryByUserId(Integer userId);
}
