package com.example.shcoolwork.controller.Posting;

import com.example.shcoolwork.Entity.DTO.PostingDTO;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.Entity.VO.PostingVO;
import com.example.shcoolwork.service.PostingService;
import com.example.shcoolwork.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/posts")
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
        log.info("查看的贴子ID");
        PostingVO postingVO=postingService.getDetail(id);
        return Result.success(postingVO);
    }
}
