package com.example.shcoolwork.Entity.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BulletinVO {

    private Integer id;
    private String userName;
    private Short enclosure;
    private Short job;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private LocalDateTime createTime;

}
