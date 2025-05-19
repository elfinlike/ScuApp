package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.Entity.VO.BulletinMessageVO;
import com.example.shcoolwork.Entity.VO.BulletinVO;

import java.time.LocalDateTime;
import java.util.List;

public interface BulletinService {
    void add(BulletinDTO bulletinDTO);

    BulletinVO getById(Integer id);

    List<BulletinVO> getGoingByType(Short type, Short enclosure);

    List<BulletinVO> getEndByType(Short type, Short enclosure);

    List<BulletinVO> getMyBulletins();

    void deleteById(Integer id);

    List<BulletinMessageVO> getMessages(LocalDateTime currentTime);
}
