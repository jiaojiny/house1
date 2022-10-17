package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
    @Reference
    private DictService dictService;

    @ResponseBody
    @GetMapping("/findZnodes")
    public Result findZnodes(@RequestParam(value = "id", defaultValue = "0") Long id) {
        List<Dict> list = dictService.findDictListByParentId(id);


        List<Map<String, Object>> collect = list.stream().map
                (dict -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", dict.getId());
                            map.put("name", dict.getName());
                            map.put("isParent", !CollectionUtils.isEmpty(dictService.findDictListByParentId(dict.getId())));
                            return map;
                        }
                ).collect(Collectors.toList());

        return Result.ok(collect);
    }

    @ResponseBody
    @GetMapping("/findDictListByParentId/{parentId}")
    public Result findDictListByParentId(@PathVariable("parentId") Long parentId) {
        List<Dict> dictList = dictService.findDictListByParentId(parentId);
        return Result.ok(dictList);
    }

}
