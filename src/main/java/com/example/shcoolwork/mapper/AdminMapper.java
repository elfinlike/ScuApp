package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where user_id=#{account} and password=#{password}")
    Admin getAdmin(String account, String password);

    @Select("select * from admin where id = #{id}")
    Admin getById(Integer id);


    void update(Admin admin);
}
