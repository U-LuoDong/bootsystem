package com.nsu.bootsystem.admin.controller;

import java.util.HashMap;
import java.util.Map;

import com.aliyun.oss.OSS;
import com.nsu.bootsystem.admin.component.Md5Encrypt;
import com.nsu.common.exception.BizCodeEnume;
import com.nsu.common.valid.AddGroup;
import com.nsu.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.nsu.bootsystem.admin.service.SysUserService;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.R;

import javax.servlet.http.HttpSession;


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
    @Autowired
    OSS ossClient;
    Md5Encrypt md5 = new Md5Encrypt();
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
    @ResponseBody
    @PostMapping("/add")
    public R addAdmin(@Validated(AddGroup.class) @RequestBody SysUserEntity sysUser){
        Map<String,String> respMap = new HashMap<>();
        //用户名唯一
        SysUserEntity user = sysUserService.queryByUserName(sysUser.getUserName());
        if(user != null){
            respMap.put("info","管理员已存在，请重新添加...");
            return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(),BizCodeEnume.VAILD_EXCEPTION.getMsg()).put("data",respMap);
        }
        // 2、密码相同
//        System.out.println(sysUser.getUserPassword()+"   "+sysUser.getConfirmPas());
        if(!sysUser.getUserPassword().equals(sysUser.getConfirmPas())){
            respMap.put("info","密码不一致，请重新输入...");
            return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(),BizCodeEnume.VAILD_EXCEPTION.getMsg()).put("data",respMap);
        }
        //以密文传入
        sysUser.setUserPassword(md5.Encrypt(sysUser.getUserPassword()));
        sysUserService.save(sysUser);
        return R.ok();
    }

    //来到员工修改页面
    @GetMapping("/edit")
    public String toEditPage(Integer userId,Model model){
        SysUserEntity user =  sysUserService.queryByUserId(userId);
        model.addAttribute("user",user);
        return "/admin/edit";
    }

    //员工修改
    @ResponseBody
    @PostMapping("/edit")
    public R editAdmin(@Validated(UpdateGroup.class) @RequestBody SysUserEntity sysUser){
        if (sysUser.getUserPassword()=="")
            sysUser.setUserPassword(null);
        sysUserService.updateById(sysUser);
        return R.ok();
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

    /**
     *  异步删除图片
     */
    @ResponseBody
    @PostMapping("/delimg")
    //@RequiresPermissions("admin:sysuser:delete")
    public R delimg(String imgurl,Integer userId){
        //针对修改页面=>修改userId对应的thumb
        if (userId != 0) {
            sysUserService.updateThumb(userId,"");
        }
        //删除例子：2020-06-09/1591700792675036fa4c27d1ed21b1fb8292aa06eddc453da3f82.jpg
        //删除OSS服务器中的图片
        ossClient.deleteObject("gulimall-luodong", imgurl);
        //向前端返回结果
        Map<String,Integer> respMap = new HashMap<>();
        respMap.put("res",1);
        return R.ok().put("data",respMap);
    }

    /**
     *  异步更新图片地址【针对修改】
     */
    @ResponseBody
    @PostMapping("/upimg")
    //@RequiresPermissions("admin:sysuser:delete")
    public R upimg(String imgurl,Integer userId){
        //针对修改页面=>修改userId对应的thumb
        sysUserService.updateThumb(userId,imgurl);
        return R.ok();
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:http://localhost:88/login";
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
