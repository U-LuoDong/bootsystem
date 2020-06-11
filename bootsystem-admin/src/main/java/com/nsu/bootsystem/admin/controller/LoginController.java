package com.nsu.bootsystem.admin.controller;

import com.nsu.bootsystem.admin.component.Md5Encrypt;
import com.nsu.bootsystem.admin.component.RandomValidateCodeUtil;
import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.nsu.bootsystem.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @GetMapping(value = "/useCode")
    public String useCode(){
        return  "login/useCode";
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
        //存储管理员thumb  用于头部修改信息
        session.setAttribute("adminThumb", user.getThumb());
        return "redirect:http://localhost:88/admin/index.html";
    }
    //首页
    @GetMapping("/index.html")
    public String index(){
        return "index/index";
    }


    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            System.out.println("获取验证码失败");
        }
    }

}
