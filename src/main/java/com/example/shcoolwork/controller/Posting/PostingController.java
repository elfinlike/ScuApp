package com.example.shcoolwork.controller.Posting;

import com.example.shcoolwork.Entity.DTO.PostingDTO;
import com.example.shcoolwork.Entity.DTO.PostingListDTO;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.Entity.VO.PostingListVO;
import com.example.shcoolwork.Entity.VO.PostingVO;
import com.example.shcoolwork.service.PostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostingController {
    @Autowired
    private PostingService postingService;


    @PostMapping("/publish")
    public Result<String> publish(@RequestBody PostingDTO postingDTO){
        log.info("前端传回的添加贴子的内容：{}",postingDTO);
        postingService.add(postingDTO);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<PostingVO> getPostDetail(@RequestParam Integer id){
        log.info("查看的贴子ID,{}", id);
        PostingVO postingVO=postingService.getDetail(id);
        return Result.success(postingVO);
    }

    @GetMapping("/list")
    public Result<List<PostingListVO>> getList(@RequestParam(required = false)String sortType,
                                               @RequestParam( name = "module",  required = false ) Integer categoryId,
//                                              @RequestParam(required = false)String lastTime
                                               @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime lastTime
                                               ){
        log.info("排序类型、分类名、最新时间分别为:{},{},{}",sortType,categoryId,lastTime);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime LastTime=LocalDateTime.parse(lastTime,formatter);
        PostingListDTO postingListDTO=PostingListDTO.builder()
                .sortType(sortType)
                .categoryId(categoryId)
                .lastTime(lastTime)
                .build();
        List<PostingListVO> postingVOs=postingService.getList(postingListDTO);
        System.out.println(postingVOs);

        return Result.success(postingVOs);
    }

    @GetMapping("/myposts")
    public Result<List<PostingListVO>> getMyPosts(@RequestParam(required = false) String title){
        log.info("自己发的贴子模糊查询"+title);
        List<PostingListVO> postingListVOS=postingService.getMyPosts(title);
        return Result.success(postingListVOS);
    }
}
