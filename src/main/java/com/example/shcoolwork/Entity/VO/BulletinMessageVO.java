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
public class BulletinMessageVO {

    private final String msgTitle="公告通知";
    private final String msgDetail="新公告已发布，请注意查看！";
    private String abstractContent;
    private LocalDateTime createTime;
}
