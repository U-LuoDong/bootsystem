package com.nsu.bootsystem.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsu.bootsystem.admin.entity.CustomerEntity;
import com.nsu.bootsystem.admin.service.CustomerService;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.R;



/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:11
 */
@RestController
@RequestMapping("admin/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("admin:customer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{custId}")
    //@RequiresPermissions("admin:customer:info")
    public R info(@PathVariable("custId") Integer custId){
		CustomerEntity customer = customerService.getById(custId);

        return R.ok().put("customer", customer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("admin:customer:save")
    public R save(@RequestBody CustomerEntity customer){
		customerService.save(customer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("admin:customer:update")
    public R update(@RequestBody CustomerEntity customer){
		customerService.updateById(customer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:customer:delete")
    public R delete(@RequestBody Integer[] custIds){
		customerService.removeByIds(Arrays.asList(custIds));

        return R.ok();
    }

}
