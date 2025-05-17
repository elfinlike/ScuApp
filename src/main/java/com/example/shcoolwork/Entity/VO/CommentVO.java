package com.example.shcoolwork.Entity.VO;


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
public class CommentVO implements Serializable {
    private String avatar;
    private String username;
    private LocalDateTime commenTime;
    private String commentDetail;
    private String abstractContent;
}
