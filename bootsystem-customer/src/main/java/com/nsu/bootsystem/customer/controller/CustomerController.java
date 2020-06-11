package com.nsu.bootsystem.customer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.nsu.common.exception.BizCodeEnume;
import com.nsu.common.valid.AddGroup;
import com.nsu.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nsu.bootsystem.customer.entity.CustomerEntity;
import com.nsu.bootsystem.customer.service.CustomerService;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.R;



/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-10 17:51:35
 */
//@RestController
//@RequestMapping("customer/customer")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //首页
    @GetMapping("/index.html")
    public String index(){
        return "index/index";
    }

    /**
     * 列表
     */
    @RequestMapping("/lst")
    //@RequiresPermissions("admin:sysuser:list")
    public String list(@RequestParam(required = false, defaultValue = "1", value = "pn") String pn
            ,@RequestParam(required = false, defaultValue = "", value = "custName") String custName
            ,@RequestParam(required = false, defaultValue = "该项表示不对客户来源进行查询", value = "custSource")String custSource
            ,@RequestParam(required = false, defaultValue = "该项表示不对所属行业进行查询", value = "custIndustry")String custIndustry
            , Model model){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("page",pn);
        params.put("limit","10");//10条数据一页
        PageUtils page = customerService.queryPage(params,custName,custSource,custIndustry);
        model.addAttribute("page",page);
//        model.addAttribute("jumpUrl", "lst?pn=");
        model.addAttribute("custNamePage",custName);
        model.addAttribute("custSourcePage",custSource);
        model.addAttribute("custIndustryPage",custIndustry);
        //1、客户来源
        ArrayList<String> custSourcee = new ArrayList<String>();
        custSourcee = customerService.queryCustSource();
        //2、所属行业
        ArrayList<String> custIndustryy = new ArrayList<String>();
        custIndustryy = customerService.queryCustIndustry();
        model.addAttribute("custSource",custSourcee);
        model.addAttribute("custIndustry",custIndustryy);
        return "/customer/lst";
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:sysuser:delete")
    public R delete(Integer custId){
        customerService.removeById(custId);
        return R.ok();
    }

    //来到客户添加页面
    @GetMapping("/add")
    public String toAddPage(){
        return "/customer/add";
    }

    //客户添加
    @ResponseBody
    @PostMapping("/add")
    public R addcustomer(@Validated(AddGroup.class) @RequestBody CustomerEntity customer){
        customerService.save(customer);
        return R.ok();
    }

    //来到员工修改页面
    @GetMapping("/edit")
    public String toEditPage(Integer custId,Model model){
        CustomerEntity customer =  customerService.queryByUserId(custId);
        model.addAttribute("customer",customer);
        return "/customer/edit";
    }

    //员工修改
    @ResponseBody
    @PostMapping("/edit")
    public R editCustomer(@Validated(UpdateGroup.class) @RequestBody CustomerEntity customer){
        customerService.updateById(customer);
        return R.ok();
    }

}
