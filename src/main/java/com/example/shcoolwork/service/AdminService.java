package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.Admin;

public interface AdminService {
    Admin check(String account, String password);

    Admin getById(Integer id);

}
