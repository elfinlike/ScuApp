package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where student_id=#{studentId}")
    User getByID(String studentId);
}
