package com.example.shcoolwork.service.Impl;

import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.mapper.AdminMapper;
import com.example.shcoolwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
