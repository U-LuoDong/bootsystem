package com.nsu.bootsystem.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.nsu.bootsystem.admin.service.SysUserService;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.R;



/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:10
 */
//@RestController
//@RequestMapping("admin/sysuser")
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/lst")
    //@RequiresPermissions("admin:sysuser:list")
    public String list(@RequestParam(required = false, defaultValue = "1", value = "pn") String pn, Model model){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("page",pn);
        params.put("limit","5");//5条数据一页
        PageUtils page = sysUserService.queryPage(params);
        model.addAttribute("page",page);
        model.addAttribute("jumpUrl", "lst?pn=");
        return "/admin/lst";
    }

    //来到员工添加页面
    @GetMapping("/add")
    public String toAddPage(){
        return "/admin/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/add")
    public String addAdmin(SysUserEntity sysUser){
        sysUserService.save(sysUser);
        return "redirect:http://localhost:88/admin/lst";
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:sysuser:delete")
    public R delete(Integer userId){
		sysUserService.removeById(userId);
        return R.ok();
    }

    /**
     *  异步修改管理员状态
     */
    @ResponseBody
    @PostMapping("/changeUserState")
    //@RequiresPermissions("admin:sysuser:delete")
    public R changeUserState(Integer id){
        SysUserEntity sysUser = sysUserService.getById(id);
        int userState = 0;
        if(sysUser.getUserState() == 0)
            userState = 1;
        sysUserService.changeUserState(id,userState);
        return R.ok();
    }




    //未使用————————————————————————————————
    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("admin:sysuser:info")
    public R info(@PathVariable("userId") Integer userId){
        SysUserEntity sysUser = sysUserService.getById(userId);

        return R.ok().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("admin:sysuser:save")
    public R save(@RequestBody SysUserEntity sysUser){
        sysUserService.save(sysUser);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("admin:sysuser:update")
    public R update(@RequestBody SysUserEntity sysUser){
        sysUserService.updateById(sysUser);

        return R.ok();
    }

}
