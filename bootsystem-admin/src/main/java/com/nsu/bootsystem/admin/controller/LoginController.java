package com.nsu.bootsystem.admin.controller;

import com.nsu.bootsystem.admin.component.Md5Encrypt;
import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.nsu.bootsystem.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    SysUserService sysUserService;
    Md5Encrypt md5 = new Md5Encrypt();

    @GetMapping(value = "/login")
    public String login(){
        return  "login/index";
    }
    @PostMapping(value = "/loginRes")
    public String loginRes(SysUserEntity sysUser, Map<String,Object> map, HttpSession session){
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(sysUser.getUserName());
        //账号不存在、密码错误
        if(user == null || !user.getUserPassword().equals(md5.Encrypt(sysUser.getUserPassword()))){
            map.put("msg","用户名或密码错误");
            return "/login/index";
        }
        session.setAttribute("loginUser",user.getUserName());
        //存储管理员id  用于头部修改信息
        session.setAttribute("adminId", user.getUserId());
        return "redirect:http://localhost:88/admin/index.html";
    }
    //首页
    @GetMapping("/index.html")
    public String index(){
        return "index/index";
    }
}
