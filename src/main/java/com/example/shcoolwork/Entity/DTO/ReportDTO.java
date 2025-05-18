package com.example.shcoolwork.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO implements Serializable {
    private Integer postId;
    private String reason;
    private String detail;
}
