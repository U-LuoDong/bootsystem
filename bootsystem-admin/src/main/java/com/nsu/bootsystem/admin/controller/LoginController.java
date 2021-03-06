package com.nsu.bootsystem.admin.controller;

import com.alibaba.cloud.context.utils.StringUtils;
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
import com.nsu.bootsystem.admin.service.RedisService;
import com.nsu.bootsystem.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(value = "/useCodeRes")
    public String useCodeRes(@RequestParam("userName") String userName, @RequestParam("tel_code")String tel_code, Map<String, Object> map, HttpSession session) {
//        System.out.println();
//        return "/login/usecode";
        Boolean check = sysUserService.checkAccount(userName);
        String code = redisService.get(userName);   //调用方法根据key获取缓存中对应的验证码
        if(check==null){
            map.put("msg", "登陆账号不存在，请重新登录!");
            return "/login/usecode";
        }else if(tel_code.equals("")) {
            map.put("msg", "手机验证码不能为空!");
            return "/login/usecode";
        }else if(StringUtils.isEmpty(code)){
            map.put("msg", "手机验证码已过期，请重新登录!");
            return "/login/usecode";
        }else if(!"".equals(code) && !code.equals(tel_code)){
            map.put("msg", "手机验证码不正确，请重新登录!");
            return "/login/usecode";
        }else{
            //用户信息
            SysUserEntity user = sysUserService.queryByUserName(userName);
            session.setAttribute("loginUser", user.getUserName());
            //存储管理员id  用于头部修改信息
            session.setAttribute("adminId", user.getUserId());
            //存储管理员thumb  用于头部修改信息
            session.setAttribute("adminThumb", user.getThumb());
            return "redirect:http://localhost:88/admin/index.html";
        }
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
        Boolean check = sysUserService.checkAccount(userName);
        if(check==null)
            return "0";
        else
            return "1";
    }
    @Autowired
    RedisService redisService;
    private static long CODE_EXPIRE_SECONDS = 60*2;    //设置验证码过期时间为2分鐘

    /**
     * 发送手机验证码 并  返回手机验证码
     */
    @ResponseBody
    @PostMapping(value = "/getTelCode")
    public Integer getTelCode(String userName) {

        //删除 为了不上传到git上
        String accessKeyId = "";
        String accessSecret ="";

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

        redisService.remove(userName);     //清除未失效的key对应的value值
        redisService.set(userName, rand+"");   //缓存新的key-value值
        redisService.expire(userName, CODE_EXPIRE_SECONDS);  //设置过期时间   CODE_EXPIRE_SECONDS
        return rand;
    }



}
