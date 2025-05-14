package com.example.shcoolwork.mapper;

import com.example.shcoolwork.Entity.DTO.PostingListDTO;
import com.example.shcoolwork.Entity.Posting;
import com.example.shcoolwork.Entity.PostingDetail;
import com.example.shcoolwork.Entity.PostingImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface PostingMapper {
    void addPosting(Posting posting);

    @Insert("insert into posting_image (posting_id,image,del_flag) values (#{postId},#{image},#{delFlag})")
    void addImage(PostingImage postingImage);

    @Insert("insert into posting_detail(detail, posting_id, contact_type,contact)" +
            "values (#{detail},#{postingId},#{contactType},#{contact})")
    void addPostDetail(PostingDetail postingDetail);

    @Select("select * from posting where id=#{id}")
    Posting getById(Integer id);


    @Select("select detail from posting_detail where posting_id=#{id}")
    String getDetialByPostId(Integer id);

    @Select("select image from posting_image where posting_id=#{id}")
    List<String> getImagesByPostId(Integer id);


    List<Posting> getHotList(PostingListDTO postingListDTO);

    List<Posting> getNewList(PostingListDTO postingListDTO);


    @Update("update posting set read_num=read_num+1 where id=#{id}")
    void addReadNum(Integer id);

    @Select("select * from posting where user_id=#{userId}")
    List<Posting> getByUserId(Integer userId);

    @Select("select * from posting where user_id=#{userId} and abstract_content like concat('%',#{title},'%')")
    List<Posting> getByUserIdAndTitle(Integer userId, String title);
}
