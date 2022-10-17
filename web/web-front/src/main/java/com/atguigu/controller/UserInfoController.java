package com.atguigu.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.en.YesOrNo;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.RegisterVo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    @Reference
    private UserInfoService userInfoService;

    @GetMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable("phone") String phone, HttpSession session) {

        //模拟一个验证码（code）
        String code = "1111";
        //把验证码放在sesion中n
        session.setAttribute("CODE", code);
        //验证码发送成功
        return Result.ok();
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpSession session) {
        /*注册完整步骤
         * 第一步：验证验证码是否正确
         * 第二步：验证手机号是否重复
         * 第三步：对密码进行加密
         *第四步：调用业务层进行数据的保存
         * */


        //从sessoion中拿出code（验证码）
        String sessionCode = (String) session.getAttribute("CODE");
        //从前台拿到code registerVo.getCode()
        if (!registerVo.getCode().equalsIgnoreCase(sessionCode)) {
            //验证码错误
            Result.build("null", ResultCodeEnum.CODE_ERROR);
        }
        //验证码正确  (根据前台输入的phone查后台phone 判断查到的phone是不是为空)
        UserInfo userInfo = userInfoService.findUserInfoByPhone(registerVo.getPhone());
        if (!ObjectUtils.isEmpty(userInfo)) {
            Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        if (!ObjectUtils.isEmpty(userInfoService.findUserInfoByNick(registerVo.getNickName()))) {
            Result.build(null, ResultCodeEnum.NICKNAME_REGISTER_ERROR);
        }
        userInfo = new UserInfo();
        //把 registerVo 的属性拷贝过来给userInfo
        BeanUtils.copyProperties(registerVo, userInfo);
        //重新设置密码
        userInfo.setPassword(MD5.encrypt(registerVo.getCode()));
        //设置status为1
        userInfo.setStatus(YesOrNo.YES.getCode());
        userInfoService.insert(userInfo);
        return Result.ok();
    }

    @PostMapping("/login")
    public Result login(@RequestBody RegisterVo registerVo, HttpSession session) {
        //根据手机号查找用户，判断手机号是否正确
        UserInfo userInfo = userInfoService.findUserInfoByPhone(registerVo.getPhone());
        if (userInfo == null) {
            //手机号错误了
            Result.build(null, ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (userInfo.getStatus() == 0) {
            //手机号被锁定了
            Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //判断密码是否正确
        if (!userInfo.getPassword().equals(MD5.encrypt(registerVo.getPhone()))) {
            Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        //登录成功，将数据保存到session中
        session.setAttribute("USER", userInfo);
        //将数据响应给前端，前端要做回显
        Map responseMapping = new HashMap();
        responseMapping.put("nickName", userInfo.getNickName());
        responseMapping.put("phone", userInfo.getPhone());

        return Result.ok(responseMapping);
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.ok();
    }


}

