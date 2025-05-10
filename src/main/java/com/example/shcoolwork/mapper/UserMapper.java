package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where student_id=#{studentId}")
    User getByID(String studentId);

    @Select("select * from user where username=#{username}")
    User getByName(String username);

    @Select("select * from user where phone=#{phone}")
    User getByPhone(String phone);

    void add(User user);

    @Select("select * from user where id=#{id}")
    User getId(Integer id);

    @Select("select * from user where id=#{userId}")
    User getByUserId(Integer userId);
}
