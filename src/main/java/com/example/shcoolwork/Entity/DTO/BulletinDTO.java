package com.example.shcoolwork.Entity.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BulletinDTO {

    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
