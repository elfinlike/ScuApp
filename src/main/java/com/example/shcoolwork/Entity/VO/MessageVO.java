package com.example.shcoolwork.Entity.VO;

import com.google.errorprone.annotations.FormatString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageVO {
    private static final String msgTitle="贴子服务";
    private static final String msgDetail="帖子内容审核已完成，发布成功！";
    private String abstractContent;
    private LocalDateTime createTime;
}
