package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.UserInfo;

import javax.jws.soap.SOAPBinding;

public interface UserInfoMapper extends BaseMapper<UserInfo>  {
    UserInfo findUserInfoByPhone(String phone);
    UserInfo findUserInfoByNick(String nick);

}
