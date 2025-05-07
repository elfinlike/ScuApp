package com.example.shcoolwork.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category implements Serializable {
    private Integer id;
    private String type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
