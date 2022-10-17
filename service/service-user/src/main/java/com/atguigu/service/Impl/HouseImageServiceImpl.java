package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.HouseImage;
import com.atguigu.mapper.HouseImageMapper;
import com.atguigu.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = HouseImageService.class)
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {
    @Autowired
    private HouseImageMapper houseImageMapper;
    @Override
    public BaseMapper<HouseImage> getEntityMapper() {
        return houseImageMapper;
    }

    @Override
    public List<HouseImage> findHouseImageListByHouseId(Long houseId) {
        return houseImageMapper.findHouseImageListByHouseId(houseId);
    }
}
