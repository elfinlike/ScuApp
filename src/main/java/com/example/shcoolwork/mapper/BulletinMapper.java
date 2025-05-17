package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Bulletin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BulletinMapper {
    void add(Bulletin bulletin);

    @Select("select * from bulletin where id=#{id}")
    Bulletin getById(Integer id);



    List<Bulletin> getGoingByType(Integer type);

    List<Bulletin> getEndByType(Integer type);
}
