package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.DTO.BulletinDTO;
import com.example.shcoolwork.Entity.VO.BulletinVO;

import java.util.List;

public interface BulletinService {
    void add(BulletinDTO bulletinDTO);

    BulletinVO getById(Integer id);

    List<BulletinVO> getGoingByType(String type);

    List<BulletinVO> getEndByType(String type);

    List<BulletinVO> getMyBulletins();

    void deleteById(Integer id);
}
