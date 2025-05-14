package com.example.shcoolwork.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostingDTO implements Serializable {
    private String abstractContent;
    private Integer categoryId;
    private String detail;
    private List<String> images;
    private Short campus;
    private Short enclosure;
    private Short allowComment;
}
