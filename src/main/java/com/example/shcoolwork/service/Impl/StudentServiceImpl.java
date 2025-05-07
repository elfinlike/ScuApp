package com.example.shcoolwork.service.Impl;


import ch.qos.logback.core.util.MD5Util;
import com.example.shcoolwork.Entity.DTO.StudentLoginDTO;
import com.example.shcoolwork.Entity.User;
import com.example.shcoolwork.mapper.UserMapper;
import com.example.shcoolwork.service.StudentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void check(String account, String password) {
        //检查账户是否存在
        //对加密之后的密码进行比较
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        StudentLoginDTO studentLoginDTO= StudentLoginDTO.builder()
                .studentId(account)
                .password(password)
                .build();
        User user=userMapper.getByID(studentLoginDTO.getStudentId());
        if (user==null){
            throw new RuntimeException("账户不存在");
        }else{
            //账户存在，判断对应密码是否相同
            if (!user.getPassword().equals(password)){
                throw new RuntimeException("密码错误");
            }
        }
    }
}
