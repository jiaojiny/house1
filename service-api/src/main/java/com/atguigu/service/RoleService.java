package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<Role> {

    Map<String, List<Role>> findRoleListMapByAdminId(Long adminId);

}
