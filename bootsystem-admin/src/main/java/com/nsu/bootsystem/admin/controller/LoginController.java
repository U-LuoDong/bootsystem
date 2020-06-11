package com.nsu.bootsystem.admin.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.nsu.bootsystem.admin.component.Md5Encrypt;
import com.nsu.bootsystem.admin.component.RandomValidateCodeUtil;
import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.nsu.bootsystem.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class LoginController {
    @Autowired
    SysUserService sysUserService;
    Md5Encrypt md5 = new Md5Encrypt();

    @GetMapping(value = "/login")
    public String login() {
        return "login/index";
    }

    @GetMapping(value = "/useCode")
    public String useCode() {
        return "login/useCode";
    }

    @PostMapping(value = "/loginRes")
    public String loginRes(SysUserEntity sysUser, Map<String, Object> map, HttpSession session) {
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(sysUser.getUserName());
        //账号不存在、密码错误
        if (user == null || !user.getUserPassword().equals(md5.Encrypt(sysUser.getUserPassword()))) {
            map.put("msg", "用户名或密码错误");
            return "/login/index";
        }
        session.setAttribute("loginUser", user.getUserName());
        //存储管理员id  用于头部修改信息
        session.setAttribute("adminId", user.getUserId());
        //存储管理员thumb  用于头部修改信息
        session.setAttribute("adminThumb", user.getThumb());
        return "redirect:http://localhost:88/admin/index.html";
    }

    //首页
    @GetMapping("/index.html")
    public String index() {
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

    /**
     * 获取验证码文字
     */
    @ResponseBody
    @PostMapping(value = "/getImgCode")
    public String getImgCode(HttpSession session) {
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if (random == null) {
            return null;
        }
        else{
            return random;
        }
    }

    /**
     * 检查账号是否存在
     */
    @ResponseBody
    @PostMapping(value = "/checkAccount")
    public String checkAccount(String userName,HttpSession session) {
        boolean check = sysUserService.checkAccount(userName);
        if(check)
            return "1";
        else
            return "0";
    }

    /**
     * 发送手机验证码 并  返回手机验证码
     */
    @ResponseBody
    @PostMapping(value = "/getTelCode")
    public Integer getTelCode(String userName) {
        String accessKeyId = "LTAI4GF8ubxDep1zmRoyGZju";
        String accessSecret ="DwTqymY3jNhePkSw9M6vppSkHrhWIg";

        int max=9999;
        int min=1000;
        Random random = new Random();
        int rand = random.nextInt(max)%(max-min+1) + min;
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("code",rand);

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", userName);
        request.putQueryParameter("SignName", "nsu客户管理系统");
        request.putQueryParameter("TemplateCode", "SMS_192821870");
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));//{\"code\":1232}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return rand;
    }



}
