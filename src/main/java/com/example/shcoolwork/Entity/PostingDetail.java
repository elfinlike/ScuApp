package com.example.shcoolwork.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostingDetail implements Serializable {
    private Integer id;
    private String detail;
    private Integer detailId;
    private Short contactType;
    private String contact;
}
