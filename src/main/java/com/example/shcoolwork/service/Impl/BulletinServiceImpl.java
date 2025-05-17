package com.example.shcoolwork.service.Impl;


import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.Entity.Bulletin;
import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.Entity.VO.BulletinVO;
import com.example.shcoolwork.mapper.AdminMapper;
import com.example.shcoolwork.mapper.BulletinMapper;
import com.example.shcoolwork.service.BulletinService;
import com.example.shcoolwork.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    BulletinMapper bulletinMapper;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public void add(BulletinDTO bulletinDTO) {
        Bulletin bulletin = new Bulletin();
        BeanUtils.copyProperties(bulletinDTO,bulletin);
        bulletin.setUserId(BaseContext.getCurrentId());

        Short job = adminMapper.getById(BaseContext.getCurrentId()).getJob();
        if(job==1){
            bulletin.setType((short) 1);
        }else {
            bulletin.setType((short) 2);
        }
        bulletin.setCreateTime(LocalDateTime.now());

        System.out.println("帖子实体类准备就绪......");
        bulletinMapper.add(bulletin);

    }

    @Override
    public BulletinVO getById(Integer id) {
        Bulletin bulletin =  bulletinMapper.getById(id);

        BulletinVO bulletinVO = BulletinVO.builder()
                .id(bulletin.getId())
                .title(bulletin.getTitle())
                .content(bulletin.getContent())
                .startTime(bulletin.getStartTime())
                .endTime(bulletin.getEndTime())
                .createTime(bulletin.getCreateTime())
                .build();

        Admin admin = adminMapper.getById(bulletin.getUserId());
        bulletinVO.setUserName(admin.getUsername());
        bulletinVO.setJob(admin.getJob());

        return bulletinVO;
    }

    @Override
    public List<BulletinVO> getGoingByType(String type) {

        int flag;
        if(type.equals("school")){
            flag=1;
        }else if(type.equals("dorm")){
            flag=2;
        }else {
            return null;
        }

        List<Bulletin>  bulletins =  bulletinMapper.getGoingByType(flag);

        return typeTransform(bulletins);
    }

    @Override
    public List<BulletinVO> getEndByType(String type) {
        int flag;
        if(type.equals("school")){
            flag=1;
        }else if(type.equals("dorm")){
            flag=2;
        }else {
            return null;
        }

        List<Bulletin>  bulletins =  bulletinMapper.getEndByType(flag);

        return typeTransform(bulletins);
    }

    @Override
    public List<BulletinVO> getMyBulletins() {
        Integer userId = BaseContext.getCurrentId();

        List<Bulletin>  bulletins =  bulletinMapper.getMyBulletins(userId);

        return typeTransform(bulletins);
    }

    @Override
    public void deleteById(Integer id) {
        bulletinMapper.deleteById(id);
    }


    private List<BulletinVO> typeTransform(List<Bulletin> bulletins){
        List<BulletinVO> bulletinVOS = new ArrayList<>();
        for (Bulletin bulletin : bulletins) {
            BulletinVO bulletinVO = BulletinVO.builder()
                    .id(bulletin.getId())
                    .title(bulletin.getTitle())
                    .content(bulletin.getContent())
                    .startTime(bulletin.getStartTime())
                    .endTime(bulletin.getEndTime())
                    .createTime(bulletin.getCreateTime())
                    .build();

            Admin admin = adminMapper.getById(bulletin.getUserId());
            bulletinVO.setUserName(admin.getUsername());
            bulletinVO.setJob(admin.getJob());
            bulletinVOS.add(bulletinVO);
        }


        //按照最新发布时间排序
        bulletinVOS.sort((o1, o2) ->
                o2.getCreateTime().compareTo(o1.getCreateTime()));

        return bulletinVOS;
    }

}
