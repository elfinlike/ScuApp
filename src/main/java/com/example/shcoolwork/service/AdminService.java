package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.Admin;
import com.example.shcoolwork.Entity.DTO.AdminDTO;

public interface AdminService {
    Admin check(String account, String password);

    Admin getById(Integer id);

    void updateInfo(AdminDTO adminDTO);
}
