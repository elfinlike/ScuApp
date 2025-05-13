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
    private String content;//posting

    private Boolean isHot;
    private Double hotScore;
    private Integer module;

    private List<String> images;//postImage
    private String avatar;//user
    private String username;//user
    private LocalDateTime createTime;//posting
    private Integer readNum;//posting






}
