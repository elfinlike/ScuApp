package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.DTO.PostingDTO;
import com.example.shcoolwork.Entity.VO.PostingVO;

public interface PostingService {
    void add(PostingDTO postingDTO);

    PostingVO getDetail(Integer id);
}
