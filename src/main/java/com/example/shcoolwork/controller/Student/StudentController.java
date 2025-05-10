package com.example.shcoolwork.controller.Student;

import com.example.shcoolwork.Entity.DTO.RegistrationDTO;
import com.example.shcoolwork.Entity.Result;
import com.example.shcoolwork.Entity.User;
import com.example.shcoolwork.mapper.UserMapper;
import com.example.shcoolwork.service.StudentService;
import com.example.shcoolwork.utils.BaseContext;
import com.example.shcoolwork.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public Result<String> Login(@RequestParam String account,@RequestParam String password){
        log.info("前端传输的账户和密码为:{},{}",account,password);
        User user=studentService.check(account,password);
        if (user!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            claims.put("studentId",user.getStudentId());
            JwtUtils.generateJwt(claims);
            String jwt=JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("账户或密码错误");
    }

    @PostMapping("/register")
    public Result<String> Register(@RequestBody RegistrationDTO registrationDTO){
        log.info("前端传回的注册数据为：{}",registrationDTO);
        String back=studentService.register(registrationDTO);
        return Result.success(back);
    }

    @GetMapping("/userInfo")
    public Result<User> getInfo(){
        Integer id= BaseContext.getCurrentId();
        User user=userMapper.getId(id);
        user.setPassword(null);
        return Result.success(user);
    }


}



