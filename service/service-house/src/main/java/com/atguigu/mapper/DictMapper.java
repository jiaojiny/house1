package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Dict;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {
    /*
    * 根据父节点查找子节点列表
    * */
    List<Dict> findDictListByParentId(Long parentId);

    List<Dict> findDictListByParentCode(String parentCode);
    /*
    * 判断是否是父节点
    * (查询父节点是id的还有几条 0为无  >0则是父节点)
    * */
    //Integer countIsParent(Long id);
}
