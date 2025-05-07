package com.example.shcoolwork.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostingImage implements Serializable {
    private Integer id;
    private Integer postId;
    private String image;
    private Short delFlag;
}
