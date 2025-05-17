package com.example.shcoolwork.Entity.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDTO implements Serializable {
    private Integer id; // 前端不需要传
    private String username;
    private String phone;
    private String avatar;
}