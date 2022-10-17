package com.atguigu.service.Impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Dict;
import com.atguigu.mapper.DictMapper;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = DictService.class)
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public BaseMapper<Dict> getEntityMapper() {
        return dictMapper;
    }

/*    public List<Map<String, Object>> findZnodes(Long id) {
            *//*

方式一：
       1. 调用mapper接口查询父节点的集合
       2. 将list 转换成 list<map>
方式二：
      list变map
      使用stream流
      list。stream。map


            * *//*
        List<Dict> dictlist = dictMapper.findListByParentId(id);


        List<Map<String, Object>> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dictlist)) {
            for (Dict dict : dictlist) {
                HashMap<String, Object> map = new HashMap();
                map.put("id", dict.getId());
                map.put("name", dict.getName());
                map.put("isParent", dictMapper.countIsParent(dict.getId()) > 0);
                list.add(map);
            }

        }
        return list;
    }*/

    @Override
    public List<Dict> findDictListByParentId(Long parentId) {
        return dictMapper.findDictListByParentId(parentId);
    }

    @Override
    public List<Dict> findDictListByParentDictCode(String parentCode) {
        return dictMapper.findDictListByParentCode(parentCode);
    }
}
