package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Role;
import com.atguigu.mapper.AdminRoleMapper;
import com.atguigu.mapper.RoleMapper;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public BaseMapper<Role> getEntityMapper() {
        return roleMapper;
    }

    @Override
    public Map<String, List<Role>> findRoleListMapByAdminId(Long adminId) {
        //查询所有角色
        List<Role> allRoleList = roleMapper.findAll();
        //根据当前用户的 id 查询当前用户已分配的所有角色的 id
        List<Long> assignRoleIdList = adminRoleMapper.findRoleIdListByAdminId(adminId);
        //3.从 allRoleList 中筛选出用户已分配的角色列表
        List<Role> assignRoleList = new ArrayList<>();
        List<Role> unAssignRoleList = new ArrayList<>();
        for (Role role : allRoleList) {
            if (assignRoleIdList.contains(role.getId())) {
                assignRoleList.add(role);
            } else {
                unAssignRoleList.add(role);
            }
        }
        Map<String, List<Role>> roleListMap = new HashMap<>();

        roleListMap.put("assignRoleList", assignRoleList);
        roleListMap.put("unAssignRoleList", unAssignRoleList);

        return roleListMap;
    }


}
