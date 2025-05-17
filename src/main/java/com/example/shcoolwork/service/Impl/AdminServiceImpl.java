package com.example.shcoolwork.service.Impl;

import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.Entity.DTO.AdminDTO;
import com.example.shcoolwork.mapper.AdminMapper;
import com.example.shcoolwork.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin check(String account, String password) {

        return adminMapper.getAdmin(account,password);
    }

    @Override
    public Admin getById(Integer id) {

        return adminMapper.getById(id);
    }

    @Override
    public void updateInfo(AdminDTO adminDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        admin.setUpdateTime(LocalDateTime.now());

        adminMapper.update(admin);
    }
}
