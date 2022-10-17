package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Community;
import com.atguigu.mapper.CommunityMapper;
import com.atguigu.mapper.HouseMapper;
import com.atguigu.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;


@Service(interfaceClass = CommunityService.class)
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public BaseMapper<Community> getEntityMapper() {
        return communityMapper;
    }

    @Override
    public void delete(Long id) {
        int count = houseMapper.findCountByCommunityId(id);
        if (count>0) {
            throw new RuntimeException("该小区还有房源，不能删除");
        }
        super.delete(id);

    }
}
