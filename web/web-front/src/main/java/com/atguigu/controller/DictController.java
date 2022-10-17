package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Reference
    private DictService dictService;

    @GetMapping("/findDictListByParentDictCode/{parentDictCode}")
    public Result findDictListByParentDictCode(@PathVariable("parentDictCode") String parentDictCode) {
        List<Dict> dictList= dictService.findDictListByParentDictCode(parentDictCode);

        return Result.ok(dictList);
    }

    @RequestMapping("/findDictListByParentId/{parentId}")
    public Result findDictListByParentId(@PathVariable("parentId") Long parentId){
        List<Dict> dictList = dictService.findDictListByParentId(parentId);
        return Result.ok(dictList);
    }
}
