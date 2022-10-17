package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.en.DictCodeEnum;
import com.atguigu.en.HouseStatus;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    private static final String PAGE_INDEX = "house/index";
    private static final String PAGE_CREATE = "house/create";
    private static final String PAGE_EDIT = "house/edit";
    private static final String PAGE_LIST = "redirect:/house";
    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    @RequestMapping
    public String index(@RequestParam Map<String, Object> filters, Model model) {
        if (!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if (filters.get("pageSize") == null) {
            filters.put("pageSize", 10);
        }
        PageInfo<House> page = houseService.findPage(filters);
        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        initializeData(model);

        return PAGE_INDEX;
    }

    private void initializeData(Model model) {
        //查询所有小区信息
        List<Community> communityList = communityService.findAll();
        model.addAttribute("communityList", communityList);
        //查询所有户型信息
        List<Dict> houseType = dictService.findDictListByParentDictCode(DictCodeEnum.HOUSETYPE.getCode());
        model.addAttribute("houseTypeList", houseType);
        //查询所有楼层信息
        List<Dict> floor = dictService.findDictListByParentDictCode(DictCodeEnum.FLOOR.getCode());
        model.addAttribute("floorList", floor);
        //查询所有建筑结构信息
        List<Dict> buildStructure = dictService.findDictListByParentDictCode(DictCodeEnum.BUILDSTRUCTURE.getCode());
        model.addAttribute("buildStructureList", buildStructure);
        //查询所有朝向信息
        List<Dict> direction = dictService.findDictListByParentDictCode(DictCodeEnum.DIRECTION.getCode());
        model.addAttribute("directionList", direction);
        //查询所有装修信息
        List<Dict> decoration = dictService.findDictListByParentDictCode(DictCodeEnum.DECORATION.getCode());
        model.addAttribute("decorationList", decoration);
        //查询所有房屋用途信息
        List<Dict> houseUse = dictService.findDictListByParentDictCode(DictCodeEnum.HOUSEUSE.getCode());
        model.addAttribute("houseUseList", houseUse);
    }

    @RequestMapping("/create")
    public String create(Model model) {
        initializeData(model);
        return PAGE_CREATE;

    }

    @PostMapping("/save")
    public String insert(House house, Model model) {
        house.setStatus(HouseStatus.UNPUBLISH.getCode());
        houseService.insert(house);
        return successPage(model, "新增成功");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        House house = houseService.getById(id);
        model.addAttribute("house", house);
        initializeData(model);
        return PAGE_EDIT;
    }

    @PostMapping("/update")
    public String update(House house, Model model) {
        houseService.update(house);
        return successPage(model, "修改成功");

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        houseService.delete(id);
        return PAGE_LIST;
    }



    @GetMapping("/publish/{id}/{status}")
        public String publishOrUnpublish(@PathVariable("id") Long id,
                              @PathVariable("status") Integer status){

        //发布和取消发布是 重新创建一个house 为了修改  重新设置一个house的两个属性 把原来的house修改成现在的house
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseService.update(house);
        return PAGE_LIST;
        }

}
