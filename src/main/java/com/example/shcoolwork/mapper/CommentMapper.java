package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where posting_id=#{id}")
    List<Comment> getByPostId(Integer id);

    @Select("select * from comment where user_id=#{id} and create_time<#{time} order by create_time")
    List<Comment> getByIdAndTime(Integer id, LocalDateTime time);


    @Insert("insert into comment(parent_id, posting_id, user_id, content, like_num, create_time, del_flag, username, image, reply_id, reply_username, avatar)" +
            "values (#{parentId},#{postingId},#{userId},#{content},#{likeNum},#{createTime},#{delFlag},#{username},#{image},#{replyId},#{replyUsername},#{avatar})")
    void addComment(Comment comment);
}
