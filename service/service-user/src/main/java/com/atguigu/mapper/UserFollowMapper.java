package com.atguigu.mapper;

import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserFollowMapper {
    //根据id和房源查询关注信息
    UserFollow findFollowById(@Param("userId") Long userId, @Param("houseId") Long houseId);

    //新增房源关注信息
    void insert(UserFollow userFollow);

    //更新房源关注信息
    void update(UserFollow userFollow);

}
