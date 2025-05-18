package com.example.shcoolwork.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private Integer id;
    private String userId;
    private String password;
    private String username;
    private Short campus;
    private Short enclosure;
    private Short job;
    private String phone;
    private String avatar;
    private Short status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
