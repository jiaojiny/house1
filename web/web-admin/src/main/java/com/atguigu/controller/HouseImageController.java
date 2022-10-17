package com.atguigu.controller;

import com.atguigu.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/houseImage")
public class HouseImageController {
    private static final String PAGE_UPLOAD_SHOW = "house/upload";

    @GetMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable("houseId") Long houseId, @PathVariable("type") Integer type, Model model){

        model.addAttribute("houseId",houseId);
        model.addAttribute("type",type);
        return PAGE_UPLOAD_SHOW;
    }
@ResponseBody
@PostMapping("/upload/{houseId}/{type}")
    public Result upload(@PathVariable("houseId")Long houseID ){

        return null;
}


}


