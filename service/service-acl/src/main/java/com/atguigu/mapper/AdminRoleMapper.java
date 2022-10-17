package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.AdminRole;

import java.util.List;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    //根据用户的id查找用户
    List<Long> findRoleIdListByAdminId(Long adminId);

}
