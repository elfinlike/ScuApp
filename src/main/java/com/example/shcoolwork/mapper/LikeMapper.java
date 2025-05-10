package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    @Select("select * from `like` where posting_id=#{postId} and user_id=#{userId}")
    Like getByUserPost(Integer postId, Integer userId);
}
