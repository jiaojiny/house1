package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Admin;
import com.atguigu.entity.AdminRole;
import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission> {
    /*
    * 根据RoleId查询权限信息
    * */
     Map<String, List<AdminRole>> findPermissionByRoleId();


}
