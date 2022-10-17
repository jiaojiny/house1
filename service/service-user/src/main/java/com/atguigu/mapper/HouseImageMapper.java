package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseImage;

import java.util.List;

public interface HouseImageMapper extends BaseMapper<HouseImage> {
    List<HouseImage> findHouseImageListByHouseId(Long houseId);
}
