package com.example.shcoolwork.service.Impl;


import com.example.shcoolwork.Entity.DTO.RegistrationDTO;
import com.example.shcoolwork.Entity.DTO.StudentLoginDTO;
import com.example.shcoolwork.Entity.User;
import com.example.shcoolwork.mapper.UserMapper;
import com.example.shcoolwork.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User check(String account, String password) {
        //检查账户是否存在
        //对加密之后的密码进行比较
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        StudentLoginDTO studentLoginDTO= StudentLoginDTO.builder()
                .studentId(account)
                .password(password)
                .build();
        User user=userMapper.getByID(studentLoginDTO.getStudentId());
        return user;
        }

    /**
     * 对学生进行注册
     * @param registrationDTO
     */
    @Override
    public String register(RegistrationDTO registrationDTO) {
        User user=new User();
        BeanUtils.copyProperties(registrationDTO,user);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        //注册时对密码进行加密处理
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        User user1=userMapper.getByName(user.getUsername());
        if (user1!=null){
            return "用户名已存在";
        }

        User userMapperByID=userMapper.getByID(user.getStudentId());
        if (userMapperByID!=null){
            return "学号已经存在";
        }

        User user2=userMapper.getByPhone(user.getPhone());
        if (user2!=null){
            return "电话号码已经绑定";
        }

        userMapper.add(user);
        return "注册成功";
    }
}
