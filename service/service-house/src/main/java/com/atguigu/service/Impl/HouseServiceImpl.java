package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.constant.AtguiguConstant;
import com.atguigu.entity.House;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;
import com.atguigu.mapper.HouseMapper;
import com.atguigu.service.HouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = HouseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService{
   @Autowired
    private HouseMapper houseMapper;

    @Override
    public BaseMapper<House> getEntityMapper() {
        return houseMapper;
    }

    @Override
    public PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //根据前台封装的hosueQueryVo条件查询结果HouseVo
        Page<HouseVo> page = (Page<HouseVo>) houseMapper.findListPage(houseQueryVo);
        return new PageInfo<>(page, AtguiguConstant.PageInfoConstant.DEFAULT_NAVIGATE_PAGES);
    }



}
