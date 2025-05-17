package com.example.shcoolwork.controller.Bulletin;


import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.Entity.VO.BulletinVO;
import com.example.shcoolwork.service.BulletinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/bulletin")
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

    public Result<BulletinVO> getBulletVO(@RequestParam Integer id){


    }

}
