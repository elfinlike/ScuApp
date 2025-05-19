package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Bulletin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BulletinMapper {
    void add(Bulletin bulletin);

    @Select("select * from bulletin where id=#{id}")
    Bulletin getById(Integer id);


    List<Bulletin> getGoingByTypeAndEnclosure(Short type, Short enclosure);

    List<Bulletin> getEndByTypeAndEnclosure(Short type, Short enclosure);

    @Select("select * from bulletin where id=#{id}")
    List<Bulletin> getMyBulletins(Integer userId);

    @Delete("delete from bulletin where id=#{id}")
    void deleteById(Integer id);

    List<Bulletin> getGoingByType(Short type);

    List<Bulletin> getEndByType(Short type);

    @Select("select * from bulletin where user_id=#{userId}")
    List<Bulletin> getByUserId(Integer userId);
}
