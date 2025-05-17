package com.example.shcoolwork.service.Impl;


import com.example.shcoolwork.Entity.Bulletin;
import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.mapper.AdminMapper;
import com.example.shcoolwork.mapper.BulletinMapper;
import com.example.shcoolwork.service.BulletinService;
import com.example.shcoolwork.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
