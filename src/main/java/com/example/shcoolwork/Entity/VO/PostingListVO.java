package com.example.shcoolwork.Entity.VO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostingListVO implements Serializable {
    private Integer id;//posting

    private String abstractContent;//posting

    private String content;//detail

    private Boolean isHot;//无需查表

    private Double hotScore;//posting

    private Integer module;//posting 替换categoryId

    private Short delFlag;//posting

    private List<String> images;//postImage
    private String avatar;//user
    private String username;//user
    private LocalDateTime createTime;//posting
    private Integer readNum;//posting






}
