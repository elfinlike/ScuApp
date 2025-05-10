package com.example.shcoolwork.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Posting implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private String abstractContent;
    private Integer likeNum;
    private Integer readNum;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Short delFlag;
    private Double hotScore;
    private Short compus;
    private Short enclosure;
    private Integer tipOff;

    private Short allowComment;
}
