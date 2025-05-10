package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where posting_id=#{id}")
    List<Comment> getByPostId(Integer id);
}
