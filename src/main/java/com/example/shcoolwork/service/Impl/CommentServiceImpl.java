package com.example.shcoolwork.service.Impl;

import com.example.shcoolwork.Entity.Comment;
import com.example.shcoolwork.Entity.Posting;
import com.example.shcoolwork.Entity.VO.CommentVO;
import com.example.shcoolwork.mapper.CommentMapper;
import com.example.shcoolwork.mapper.PostingMapper;
import com.example.shcoolwork.service.CodeService;
import com.example.shcoolwork.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostingMapper postingMapper;
    @Override
    public List<CommentVO> getComments(Integer id, LocalDateTime time) {
        List<CommentVO> commentVOS=new ArrayList<>();
        List<Comment> comments=commentMapper.getByIdAndTime(id,time);
        for (Comment comment : comments) {
            CommentVO commentVO=CommentVO.builder()
                    .avatar(comment.getImage())
                    .username(comment.getUsername())
                    .commenTime(comment.getCreateTime())
                    .commentDetail(comment.getContent())
                    .build();

            Posting posting=postingMapper.getById(id);
            commentVO.setAbstractContent(posting.getAbstractContent());
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }
}
