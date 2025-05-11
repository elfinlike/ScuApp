package com.example.shcoolwork.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
private Integer id;
private String username;
private String password;
private Short compus;
private Short enclosure;
private Short unit;
private String phone;
private String avatar;
private LocalDateTime createTime;
private LocalDateTime updateTime;
private Short status;
private Short delFlag;
private String studentId;
}
