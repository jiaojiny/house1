package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;

import java.util.List;


public interface HouseMapper extends BaseMapper<House>{
    List<HouseVo> findListPage(HouseQueryVo houseQueryVo);
    /*
    * 根据房源表（hse_house）中的 小区id(community_id) 查询 小区中有没有房源，有房源的话不能删除小区
    *
    * */
    Integer findCountByCommunityId(Long communityId);
}
