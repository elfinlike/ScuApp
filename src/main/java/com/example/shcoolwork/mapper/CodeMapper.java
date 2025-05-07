package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Code;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CodeMapper {
    @Delete("delete from code where phone=#{phone}")
    void delete(String phone);

    @Select("SELECT * from code where phone=#{phone}")
    Code getByPhone(String phone);

    @Insert("insert into code(phone, code) values (#{phone},#{code})")
    void add(String phone, String code);
}
