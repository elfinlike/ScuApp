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
public class Bulletin implements Serializable {
    private Integer id;
    private String userId;
    private String title;
    private Short type;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;


}
