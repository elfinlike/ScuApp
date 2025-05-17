package com.example.shcoolwork.service.Impl;

import com.example.shcoolwork.Entity.Comment;
import com.example.shcoolwork.Entity.DTO.CommentDTO;
import com.example.shcoolwork.Entity.Posting;
import com.example.shcoolwork.Entity.User;
import com.example.shcoolwork.Entity.VO.CommentVO;
import com.example.shcoolwork.mapper.CommentMapper;
import com.example.shcoolwork.mapper.PostingMapper;
import com.example.shcoolwork.mapper.UserMapper;
import com.example.shcoolwork.service.CodeService;
import com.example.shcoolwork.service.CommentService;
import com.example.shcoolwork.utils.BaseContext;
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

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取评论
     * @param time
     * @return
     */
    @Override
    public List<CommentVO> getComments(LocalDateTime time) {
        List<CommentVO> commentVOS=new ArrayList<>();
        //先根据用户id拿到所有满足要求的发的贴子的id
        List<Integer> ids=postingMapper.getPostIdByUserId(BaseContext.getCurrentId(),time);
        //然后根据获得的贴子id拿取评论
        for (Integer id : ids) {
            List<Comment> comments=commentMapper.getByPostId(id);
            //获取文章标题
            Posting posting=postingMapper.getById(id);
            for (Comment comment : comments) {
                CommentVO commentVO=CommentVO.builder()
                        .commenTime(comment.getCreateTime())
                        .avatar(comment.getAvatar())
                        .commentDetail(comment.getContent())
                        .username(comment.getUsername())
                        .abstractContent(posting.getAbstractContent())
                        .build();
                commentVOS.add(commentVO);
            }
        }
        return commentVOS;
    }

    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment=Comment.builder()
                .postingId(commentDTO.getPostId())
                .image(commentDTO.getImage())
                .Content(commentDTO.getContent())
                .build();
        User user=userMapper.getId(BaseContext.getCurrentId());
        comment.setUserId(user.getId());
        comment.setUsername(user.getUsername());
        comment.setAvatar(user.getAvatar());
        comment.setCreateTime(LocalDateTime.now());
        comment.setLikeNum(0);
        commentMapper.addComment(comment);
    }
}
