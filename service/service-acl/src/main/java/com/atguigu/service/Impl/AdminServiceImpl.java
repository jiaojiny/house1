package com.atguigu.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Admin;
import com.atguigu.mapper.AdminMapper;
import com.atguigu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public BaseMapper<Admin> getEntityMapper() {
        return adminMapper;
    }
}
