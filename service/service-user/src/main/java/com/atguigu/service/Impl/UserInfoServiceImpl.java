package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseService;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.UserInfo;
import com.atguigu.mapper.UserInfoMapper;
import com.atguigu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public BaseMapper<UserInfo> getEntityMapper() {
        return userInfoMapper;
    }

    public UserInfo findUserInfoByPhone(String phone) {
        return userInfoMapper.findUserInfoByPhone(phone);
    }

    @Override
    public UserInfo findUserInfoByNick(String nick) {
        return userInfoMapper.findUserInfoByNick(nick);
    }


}
