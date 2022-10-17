package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFollowService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/userFollow")
@Controller
public class UserFollowController {
    @Reference
    private UserFollowService userFollowService;

    @GetMapping("/auth/follow/{houseId}")
    public Result findFollowById(@PathVariable("houseId") Long houseId, HttpSession session) {
        //1.根据  userid 和 houseId 查询之前有没有关注过
        //1.1 获取该用户的 userId
        UserInfo userInfo = (UserInfo) session.getAttribute("USER");
        UserFollow userFollow = userFollowService.findFollowById(userInfo.getId(), houseId);
        if (userFollow != null) {
            //2.如果关注过，我们需要更新这条信息的  is_deleted = 0
            userFollow.setIsDeleted(0);
            userFollowService.update(userFollow);
        }
        //3.如果没有关注过，我们需要新增一条数据
        userFollow = new UserFollow();
        userFollow.setUserId(userInfo.getId());
        userFollow.setHouseId(houseId);
        userFollowService.insert(userFollow);

        return Result.ok();
    }
}
