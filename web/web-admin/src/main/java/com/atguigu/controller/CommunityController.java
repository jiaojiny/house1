package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.en.DictCodeEnum;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {
    private static final String PAGE_INDEX = "community/index";
    private static final String PAGE_CREATE = "community/create";
    private static final String PAGE_EDIT = "community/edit";
    private static final String PAGE_DELETE = "redirect:/community";

    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    @RequestMapping
    public String index(@RequestParam Map<String, Object> filters, Model model) {
        if (filters.get("pageNum") == null) {
            filters.put("pageNum", 1);
        }
        if (filters.get("pageSize") == null) {
            filters.put("pageSize", 10);
        }
        List<Dict> areaList = dictService.findDictListByParentDictCode("beijing");
        PageInfo<Community> pageInfo = communityService.findPage(filters);
        if (ObjectUtils.isEmpty(filters.get("areaId"))) {

            filters.put("areaId", 0);
        }
        if (ObjectUtils.isEmpty(filters.get("plateId"))) {

            filters.put("plateId", 0);
        }

        model.addAttribute("filters", filters);
        model.addAttribute("areaList", areaList);

        model.addAttribute("page", pageInfo);

        return PAGE_INDEX;
    }


    @GetMapping("/create")
    public String insert(Model model) {
        List<Dict> areaList = dictService.findDictListByParentDictCode(DictCodeEnum.BEIJING.getCode());
        model.addAttribute("areaList", areaList);
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(Community community, Model model) {
        communityService.insert(community);
        return successPage(model, "新增成功");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        Community community = communityService.getById(id);
        model.addAttribute("community",community);
        List<Dict> dictList = dictService.findDictListByParentDictCode(DictCodeEnum.BEIJING.getCode());
        model.addAttribute("areaList",dictList);
        return PAGE_EDIT;
    }
    @PostMapping("/update")
    public String update(Community community,Model model){
        communityService.update(community);
        return successPage(model,"修改成功");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        communityService.delete(id);
        return PAGE_DELETE;
    }
}

