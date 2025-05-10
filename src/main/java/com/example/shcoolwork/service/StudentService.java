package com.example.shcoolwork.service;

import com.example.shcoolwork.Entity.DTO.RegistrationDTO;
import com.example.shcoolwork.Entity.User;

public interface StudentService {
    User check(String account, String password);

    String register(RegistrationDTO registrationDTO);
}
