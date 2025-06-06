package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.DTO.PostingDTO;
import com.example.shcoolwork.Entity.DTO.PostingListDTO;
import com.example.shcoolwork.Entity.DTO.ReportDTO;
import com.example.shcoolwork.Entity.VO.MessageVO;
import com.example.shcoolwork.Entity.VO.PostingListVO;
import com.example.shcoolwork.Entity.VO.PostingVO;

import java.time.LocalDateTime;
import java.util.List;

public interface PostingService {
    void add(PostingDTO postingDTO);

    PostingVO getDetail(Integer id);

    List<PostingListVO> getList(PostingListDTO postingListDTO);

    List<PostingListVO> getMyPosts(String title);


    void addReport(ReportDTO reportDTO);

    List<MessageVO> getMessages(LocalDateTime currentTime);

    List<PostingListVO> getListDorm(Integer enclosure, LocalDateTime lastTime);
}
