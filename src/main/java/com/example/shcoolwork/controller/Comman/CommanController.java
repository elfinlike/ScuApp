package com.example.shcoolwork.controller.Comman;

import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@RestController
@Slf4j
public class CommanController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        //调用阿里云OSS工具类来实现文件上传；
        String url=aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url是：{}",url);

        return Result.success(url);
    }
}
