package com.example.shcoolwork.controller.Admin;


import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.mapper.AdminMapper;

import com.example.shcoolwork.service.AdminService;

import com.example.shcoolwork.utils.BaseContext;
import com.example.shcoolwork.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @GetMapping("/login")
    public Result<String> Login(@RequestParam String account, @RequestParam String password){
        log.info("前端传输的账户和密码为:{},{}",account,password);
        Admin admin =adminService.check(account,password);
        if (admin!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",admin.getId());
            claims.put("username",admin.getUsername());
            claims.put("adminId",admin.getUserId());
            JwtUtils.generateJwt(claims);
            String jwt=JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("账户或密码错误");
    }


    @GetMapping("/userInfo")
    public Result<Admin> getInfo(){
        Integer id= BaseContext.getCurrentId();
        Admin admin=adminService.getById(id);
        log.info("获取管理员信息：{}", admin);
        admin.setPassword(null);
        return Result.success(admin);
    }


}
