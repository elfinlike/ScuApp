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
public class Comment implements Serializable {
    private Integer id;
    private Integer parentId;
    private Integer postingId;
    private Integer userId;
    private String Content;
    private Integer likeNum;
    private LocalDateTime createTime;
    private Short delFlag;
    private String username;
    private String avatar;
    private String image;
    private Integer replyId;
    private String replyUsername;
}
