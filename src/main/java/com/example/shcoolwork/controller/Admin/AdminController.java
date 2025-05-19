package com.example.shcoolwork.controller.Admin;


import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.Entity.DTO.AdminDTO;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.Entity.VO.AdminVO;
import com.example.shcoolwork.Entity.VO.BulletinMessageVO;
import com.example.shcoolwork.Entity.VO.MessageVO;
import com.example.shcoolwork.mapper.AdminMapper;

import com.example.shcoolwork.service.AdminService;

import com.example.shcoolwork.service.BulletinService;
import com.example.shcoolwork.utils.BaseContext;
import com.example.shcoolwork.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private BulletinService bulletinService;


    @GetMapping("/login")
    public Result<Object> Login(@RequestParam String account, @RequestParam String password){
        log.info("前端传输的账户和密码为:{},{}",account,password);
        if(account.length()!=8){
            return Result.error("用户名格式错误");
        }

        Admin admin =adminService.check(account,password);
        if (admin!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",admin.getId());
            claims.put("username",admin.getUsername());
            claims.put("adminId",admin.getUserId());
            JwtUtils.generateJwt(claims);
            String jwt=JwtUtils.generateJwt(claims);
            AdminVO adminVO = new AdminVO(jwt,admin.getJob());
            return Result.success(adminVO);
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

    @PutMapping("/update_info")
    public Result<String> updateAdmin(@RequestBody AdminDTO adminDTO){
        log.info("前端要修改的信息是：{}",adminDTO);
        Integer id= BaseContext.getCurrentId();
        adminDTO.setId(id);
        adminService.updateInfo(adminDTO);

        return Result.success();
    }


    @GetMapping("/message/system")
    public Result<List<BulletinMessageVO>> getMessage(@RequestParam(required = false) LocalDateTime currentTime){
        log.info("前端传回的当前时间为"+currentTime);
        if (currentTime==null)
            currentTime=LocalDateTime.now();
        List<BulletinMessageVO> messageVOS=bulletinService.getMessages(currentTime);
        System.out.println(messageVOS);
        return Result.success(messageVOS);
    }

}
