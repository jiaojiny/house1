package com.atguigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.UserFollow;
import com.atguigu.mapper.UserFollowMapper;
import com.atguigu.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = UserFollowService.class)
public class UserFollowServiceImpl implements UserFollowService {
    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    public UserFollow findFollowById(Long userId, Long houseId) {
        return userFollowMapper.findFollowById(userId, houseId);
    }

    @Override
    public void insert(UserFollow userFollow) {
        userFollowMapper.insert(userFollow);
    }

    @Override
    public void update(UserFollow userFollow) {
        userFollowMapper.update(userFollow);
    }
}
