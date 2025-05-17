package com.example.shcoolwork.controller.Bulletin;


import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.Entity.VO.BulletinVO;
import com.example.shcoolwork.service.BulletinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/bulletins")
@CrossOrigin("*")
public class BulletinController {

    @Autowired
    BulletinService bulletinService;

    @PostMapping("/publish")
    public Result<String> publish(@RequestBody BulletinDTO bulletinDTO){
        log.info("前端添加的公告内容：{}", bulletinDTO);
        bulletinService.add(bulletinDTO);
        return Result.success("发布成功");
    }

    @GetMapping("/detail")
    public Result<BulletinVO> getBulletDetail(@RequestParam Integer id){
        log.info("前端查询公告id:{}",id);
        BulletinVO bulletinVO = bulletinService.getById(id);
        return Result.success(bulletinVO);
    }

    @GetMapping("/list/{type}/going")
    public Result<List<BulletinVO>> getGoingByType(@PathVariable String type){
        List<BulletinVO> list =  bulletinService.getGoingByType(type);

        return Result.success(list);
    }

    @GetMapping("/list/{type}/end")
    public Result<List<BulletinVO>> getEndByType(@PathVariable String type) {
        List<BulletinVO> list = bulletinService.getEndByType(type);

        return Result.success(list);
    }

    @GetMapping("/list/myLists")
    public Result<List<BulletinVO>> getMyBulletins(){
        List<BulletinVO> list = bulletinService.getMyBulletins();

        return Result.success(list);

    }


}
