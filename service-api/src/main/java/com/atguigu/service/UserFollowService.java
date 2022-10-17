package com.atguigu.service;

import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;

public interface UserFollowService {
    UserFollow findFollowById(Long userId, Long houseId);

    void insert(UserFollow userFollow);

    void update(UserFollow userFollow);
}
