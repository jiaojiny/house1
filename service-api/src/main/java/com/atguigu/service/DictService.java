package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;


public interface DictService extends BaseService<Dict> {

    List<Dict> findDictListByParentId(Long id);
    /*
    *根据父节点的dictCode查询子节点的列表
    *  */
    List<Dict> findDictListByParentDictCode(String parentCode);

}
