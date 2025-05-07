package com.example.shcoolwork.controller;

import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public Result<String> Login(@RequestParam String account,@RequestParam String password){
        log.info("前端传输的账户和密码为:{},{}",account,password);
        studentService.check(account,password);
        return Result.success("登录成功");
    }

}



