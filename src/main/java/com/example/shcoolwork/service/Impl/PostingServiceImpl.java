package com.example.shcoolwork.service.Impl;

import com.example.shcoolwork.Entity.*;
import com.example.shcoolwork.Entity.DTO.PostingDTO;
import com.example.shcoolwork.Entity.DTO.PostingListDTO;
import com.example.shcoolwork.Entity.DTO.ReportDTO;
import com.example.shcoolwork.Entity.VO.PostingListVO;
import com.example.shcoolwork.Entity.VO.PostingVO;
import com.example.shcoolwork.mapper.CommentMapper;
import com.example.shcoolwork.mapper.LikeMapper;
import com.example.shcoolwork.mapper.PostingMapper;
import com.example.shcoolwork.mapper.UserMapper;
import com.example.shcoolwork.service.PostingService;
import com.example.shcoolwork.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    private PostingMapper postingMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LikeMapper likeMapper;
    /**
     * 需要添加贴子的参数
     * @param postingDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(PostingDTO postingDTO) {
        //先将数据存到posting表中，得到返回的Id值；
        Posting posting=new Posting();
        BeanUtils.copyProperties(postingDTO,posting);
        posting.setUserId(BaseContext.getCurrentId());
        posting.setEnclosure(userMapper.getId(BaseContext.getCurrentId()).getEnclosure());
        posting.setCreateTime(LocalDateTime.now());
        posting.setUpdateTime(LocalDateTime.now());
        posting.setModule(postingDTO.getCategoryId());
        System.out.println("1...");
        postingMapper.addPosting(posting);
        System.out.println("2........");

        //插入图片
        for (String image : postingDTO.getImages()) {
            PostingImage postingImage=PostingImage.builder()
                    .postId(posting.getId())
                    .image(image)
                    .build();
            System.out.println("3......");
            postingMapper.addImage(postingImage);
        }

        //插入详细内容
        PostingDetail postingDetail=PostingDetail.builder()
                .postingId(posting.getId())
                .detail(postingDTO.getDetail())
                .build();
        postingMapper.addPostDetail(postingDetail);
    }

    @Override
    public PostingVO getDetail(Integer id) {
        //先获取贴子的大概信息
        Posting posting=postingMapper.getById(id);
        if (posting==null){
            throw new RuntimeException("当前ID不存在记录");
        }

        PostingVO postingVO=PostingVO.builder()
                .abstractContent(posting.getAbstractContent())
                .createTime(posting.getCreateTime())
                .likeNum(posting.getLikeNum())
                .readNum(posting.getReadNum())
                .build();

        //根据贴子ID获取详细内容
        String detail=postingMapper.getDetialByPostId(id);

        postingVO.setDetail(detail);

        //获取图片列表
        List<String> images=postingMapper.getImagesByPostId(id);

        postingVO.setImages(images);
        //获取评论列表
        List<Comment> comments=commentMapper.getByPostId(id);

        postingVO.setComments(comments);

        //获取发帖人的信息
        User user=userMapper.getByUserId(posting.getUserId());

        postingVO.setAvatar(user.getAvatar());
        postingVO.setUsername(user.getUsername());

        //本人是否点赞
        Like like= likeMapper.getByUserPost(id,BaseContext.getCurrentId());
        if(like!=null)
            postingVO.setLiked(true);
        else
            postingVO.setLiked(false);

        postingMapper.addReadNum(id);
        return postingVO;
    }

    @Override
    public List<PostingListVO> getList(PostingListDTO postingListDTO) {
        //首先根据分类来进行获取数据，两种，一种是最新消息，另一种是最热消息；
        List<PostingListVO> postingListVOS=new ArrayList<>();
        List<Posting> postings=new ArrayList<>();
        if(postingListDTO.getSortType().equals("hot")){
            postings=postingMapper.getHotList(postingListDTO);
        } else if (postingListDTO.getSortType().equals("new")) {
            postings=postingMapper.getNewList(postingListDTO);
        }



        for (Posting posting : postings) {
            PostingListVO postingListVO=PostingListVO.builder()
                    .id(posting.getId())
                    .abstractContent(posting.getAbstractContent())
                    .readNum(posting.getReadNum())
                    .createTime(posting.getCreateTime())
                    .module(posting.getModule())
                    .hotScore(posting.getHotScore())
                    .isHot(posting.getHotScore() > 100)
                    .content(postingMapper.getDetialByPostId(posting.getId()))
                    .build();
            List<String> images=postingMapper.getImagesByPostId(posting.getId());
            postingListVO.setImages(images);
            User user=userMapper.getId(posting.getUserId());
            postingListVO.setUsername(user.getUsername());
            postingListVO.setAvatar(user.getAvatar());
            postingListVOS.add(postingListVO);
        }
        return postingListVOS;
    }

    @Override
    public List<PostingListVO> getMyPosts(String title) {
        Integer userId=BaseContext.getCurrentId();
        List<Posting> postings=postingMapper.getByUserIdAndTitle(userId,title);
        List<PostingListVO> postingListVOS=new ArrayList<>();
        for (Posting posting : postings) {
            PostingListVO postingListVO=PostingListVO.builder()
                    .id(posting.getId())
                    .abstractContent(posting.getAbstractContent())
                    .hotScore(posting.getHotScore())
                    .module(posting.getModule())
                    .delFlag(posting.getDelFlag())
                    .createTime(posting.getCreateTime())
                    .readNum(posting.getReadNum())
                    .isHot(posting.getHotScore()>100)
                    .content(postingMapper.getDetialByPostId(posting.getId()))
                    .images(postingMapper.getImagesByPostId(posting.getId()))
                    .build();

            User user=userMapper.getId(userId);
            postingListVO.setAvatar(user.getAvatar());
            postingListVO.setUsername(user.getUsername());
            postingListVOS.add(postingListVO);
        }
        return postingListVOS;
    }

    @Override
    public void addReport(ReportDTO reportDTO) {
        postingMapper.addReport(reportDTO.getPostId());
    }
}
