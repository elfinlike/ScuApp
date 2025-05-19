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

    /**
     * 添加公告
     * @param bulletinDTO
     * @return 添加成功
     */
    @PostMapping("/publish")
    public Result<String> publish(@RequestBody BulletinDTO bulletinDTO){
        log.info("前端添加的公告内容：{}", bulletinDTO);
        bulletinService.add(bulletinDTO);
        return Result.success();
    }

    /**
     *
     * @param id 要查询公告的ID
     * @return
     */
    @GetMapping("/detail")
    public Result<BulletinVO> getBulletDetail(@RequestParam Integer id){
        log.info("前端查询公告id:{}",id);
        BulletinVO bulletinVO = bulletinService.getById(id);
        return Result.success(bulletinVO);
    }

    /**
     *
     * @param type 查询公告的类型
     * @param enclosure 查询公告的user的围合
     * @return 公告列表
     */

    @GetMapping("/list/going")
    public Result<List<BulletinVO>> getGoingByType(@RequestParam Short type, @RequestParam Short enclosure){
        List<BulletinVO> list =  bulletinService.getGoingByType(type, enclosure);

        return Result.success(list);
    }

    /**
     *
     * @param type type 查询公告的类型
     * @param enclosure enclosure 查询公告的user的围合
     * @return 公告列表
     */
    @GetMapping("/list/end")
    public Result<List<BulletinVO>> getEndByType(@RequestParam Short type, @RequestParam Short enclosure) {
        List<BulletinVO> list = bulletinService.getEndByType(type, enclosure);

        return Result.success(list);
    }


    /**
     * 查询自己发布的公告
     * @return  自己发布的公告
     */
    @GetMapping("/list/myLists")
    public Result<List<BulletinVO>> getMyBulletins(){
        List<BulletinVO> list = bulletinService.getMyBulletins();

        return Result.success(list);

    }

    /**
     * 删除公告
     * @param id 要删除公告的id
     * @return 删除成功
     */
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteBulletins(@PathVariable Integer id){
        log.info("前端要删除的公告id");
        bulletinService.deleteById(id);

        return Result.success("删除成功");
    }



}
