package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AdminController {
    private static final String PAGE_ASSIGN_SHOW = "admin/assignShow";
    @Reference
private RoleService roleService;
    @GetMapping("/assignShow/{id}")
    public String assignShow(@PathVariable("id") Long id, Model model){
        //调用业务层的方法查询已分配和未分配的角色
        Map<String, List<Role>> roleListMap = roleService.findRoleListMapByAdminId(id);
        //将查询到的数据放到请求域中
        model.addAllAttributes(roleListMap);

        return PAGE_ASSIGN_SHOW;
    }








}

