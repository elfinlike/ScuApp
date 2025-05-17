package com.example.shcoolwork.Entity.VO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminVO implements Serializable {
    private String jwt; //令牌
    private Short job; // 职位
}
