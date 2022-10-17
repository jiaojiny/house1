package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;
import com.atguigu.result.Result;
import com.atguigu.service.CommunityService;
import com.atguigu.service.HouseBrokerService;
import com.atguigu.service.HouseImageService;
import com.atguigu.service.HouseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Reference
    private HouseService houseService;

    public HouseController() {
    }


    /*
     * 在这个方法中  请求全部封装在了houseQueryVo中
     *            响应全部封装在了houseVo中
     *
     * */
    @PostMapping("/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo,
                               @PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize) {
        PageInfo<HouseVo> page = houseService.findListPage(pageNum, pageSize, houseQueryVo);
        return Result.ok(page);
    }

    //    查看房源信息 用的是异步请求，携带的数据是houseId

    @Reference
    private CommunityService communityService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseImageService houseImageService;



//查看房源的详细信息：包括房源信息，小区信息，经纪人信息，
    //需要把他们放到一个map里面
    //
    @GetMapping("/info/{houseId}")
    public Result list(@PathVariable("houseId") Long houseId) {
        House house = houseService.getById(houseId);
        Community community = communityService.getById(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findHouseBrokerListByHouseId(houseId);
        List<HouseImage> houseImageList = houseImageService.findHouseImageListByHouseId(houseId);


        Map<String,Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImageService",houseImageList);
        //该房源的关注情况，默认false
        map.put("isFollow",false);


        return Result.ok(map);
    }


}
