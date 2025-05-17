package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.VO.CommentVO;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentService {
    List<CommentVO> getComments(LocalDateTime time);
}
