package com.nsu.bootsystem.customer.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.Query;

import com.nsu.bootsystem.customer.dao.CustomerDao;
import com.nsu.bootsystem.customer.entity.CustomerEntity;
import com.nsu.bootsystem.customer.service.CustomerService;
import org.springframework.util.StringUtils;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    @Override 
    public PageUtils queryPage(Map<String, Object> params,String custName,String custSource,String custIndustry) {

        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
//        System.out.println("custName="+custName+"custSource="+custSource+"custIndustry="+custIndustry);
        //获取查询条件[总共有八种]
        if(!custName.equals("") && (!custSource.equals("该项表示不对客户来源进行查询"))
                && (!custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.eq("cust_source", custSource).eq("cust_industry", custIndustry).like("cust_name", custName);
//            System.out.println("88");
        }else if(custName.equals("") &&  (!custSource.equals("该项表示不对客户来源进行查询"))
                && (!custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.eq("cust_source", custSource).eq("cust_industry", custIndustry);
//            System.out.println("77");
        }else if(!custName.equals("") &&  (custSource.equals("该项表示不对客户来源进行查询"))
                && (!custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.eq("cust_industry", custIndustry).like("cust_name", custName);
//            System.out.println("66");
        }else if(!custName.equals("") && (!custSource.equals("该项表示不对客户来源进行查询"))
                && (custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.eq("cust_source", custSource).like("cust_name", custName);
//            System.out.println("55");
        }else if(custName.equals("") && (custSource.equals("该项表示不对客户来源进行查询"))
                && (!custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.eq("cust_industry", custIndustry);
//            System.out.println("44");
        }else if(custName.equals("") && (!custSource.equals("该项表示不对客户来源进行查询"))
                && (custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.eq("cust_source", custSource);
//            System.out.println("33");
        }else if(!custName.equals("") && (custSource.equals("该项表示不对客户来源进行查询"))
                && (custIndustry.equals("该项表示不对所属行业进行查询"))){
            queryWrapper.like("cust_name", custName);
//            System.out.println("11111111111111111");
        }else{
//            System.out.println("22222222");
        }
//        System.out.println("-----------");
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public ArrayList<String> queryCustSource() {
        return baseMapper.queryCustSource();
    }

    @Override
    public ArrayList<String> queryCustIndustry() {
        return  baseMapper.queryCustIndustry();
    }

}