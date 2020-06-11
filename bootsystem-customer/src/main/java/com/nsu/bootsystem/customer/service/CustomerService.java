package com.nsu.bootsystem.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nsu.common.utils.PageUtils;
import com.nsu.bootsystem.customer.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.Map;

/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-10 17:51:35
 */
public interface CustomerService extends IService<CustomerEntity> {

    PageUtils queryPage(Map<String, Object> params,String custName,String custSource,String custIndustry);

    ArrayList<String> queryCustSource();

    ArrayList<String> queryCustIndustry();
}

