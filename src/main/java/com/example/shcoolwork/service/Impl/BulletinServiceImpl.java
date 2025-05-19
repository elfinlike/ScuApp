package com.example.shcoolwork.service.Impl;


import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.Entity.Bulletin;
import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.Entity.Posting;
import com.example.shcoolwork.Entity.VO.BulletinMessageVO;
import com.example.shcoolwork.Entity.VO.BulletinVO;
import com.example.shcoolwork.Entity.VO.MessageVO;
import com.example.shcoolwork.mapper.AdminMapper;
import com.example.shcoolwork.mapper.BulletinMapper;
import com.example.shcoolwork.service.BulletinService;
import com.example.shcoolwork.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
        bulletin.setStartTime(LocalDateTime.of(bulletinDTO.getStartTime(), LocalTime.MIN));
        bulletin.setEndTime(LocalDateTime.of(bulletinDTO.getEndTime(), LocalTime.MAX));
        System.out.println(bulletin);
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
        bulletinVO.setEnclosure(admin.getEnclosure());

        return bulletinVO;
    }

    @Override
    public List<BulletinVO> getGoingByType(Short type, Short enclosure) {

        if(type==1){
            List<Bulletin>  bulletins = bulletinMapper.getGoingByType(type);
            return typeTransform(bulletins);

        }else if(type==2){
            List<Bulletin>  bulletins =  bulletinMapper.getGoingByTypeAndEnclosure(type, enclosure);
            return typeTransform(bulletins);
        }
            return null;

    }

    @Override
    public List<BulletinVO> getEndByType(Short type, Short enclosure) {

        if (type == 1) {
            List<Bulletin>  bulletins = bulletinMapper.getEndByType(type);
            return typeTransform(bulletins);
        }else if(type ==2){
            List<Bulletin>  bulletins =  bulletinMapper.getEndByTypeAndEnclosure(type, enclosure);
            return typeTransform(bulletins);
        }

        return null;

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

    @Override
    public List<BulletinMessageVO> getMessages(LocalDateTime currentTime) {
        Integer userId=BaseContext.getCurrentId();
        List<BulletinMessageVO> messageVOS=new ArrayList<>();
        List<Bulletin> bulletins=bulletinMapper.getByUserId(userId);
        for (Bulletin bulletin : bulletins) {
            BulletinMessageVO messageVO= BulletinMessageVO.builder()
                    .abstractContent(bulletin.getContent())
                    .createTime(bulletin.getCreateTime())
                    .build();
            messageVOS.add(messageVO);
        }
        return messageVOS;
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
            bulletinVO.setEnclosure(admin.getEnclosure());

            bulletinVOS.add(bulletinVO);
        }

        //按照最新发布时间排序
        bulletinVOS.sort((o1, o2) ->
                o2.getCreateTime().compareTo(o1.getCreateTime()));

        return bulletinVOS;
    }

}
