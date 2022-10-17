package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 包名:com.atguigu.controller
 *
 * @author Leevi
 * 日期2022-09-29  14:13
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Reference
    private RoleService roleService;
    private static final String PAGE_INDEX = "role/index";

    private static final String LIST_ACTION = "redirect:/role";
    private static final String PAGE_EDIT = "role/edit";

    @RequestMapping
    public String index(@RequestParam Map<String,Object> filters, Model model){
        //1. 调用业务层的方法进行分页查询
        PageInfo<Role> pageInfo = roleService.findPage(filters);
        //2. 将查询到的分页结果存储到请求域
        model.addAttribute("page",pageInfo);
        //3. 将搜索条件存储到请求域
        model.addAttribute("filters",filters);
        //4. 返回显示页面的逻辑视图
        return PAGE_INDEX;
    }

    @PostMapping("/save")
    public String save(Role role,Model model){
        //1. 调用业务层的方法添加角色信息
        roleService.insert(role);
        return successPage(model,"新增角色信息成功");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        //1. 调用业务层的方法根据id删除角色
        roleService.delete(id);
        //2. 重定向访问角色管理的首页
        return LIST_ACTION;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        //1. 调用业务层的方法根据id查询角色信息
        Role role = roleService.getById(id);
        //2. 将查询到的角色信息存储到请求域
        model.addAttribute("role",role);
        //3. 显示修改角色页面
        return PAGE_EDIT;
    }

    @PostMapping("/update")
    public String update(Role role,Model model){
        //1. 调用业务层的方法根据id对角色进行修改
        roleService.update(role);
        return successPage(model,"修改角色信息成功");
    }
}
