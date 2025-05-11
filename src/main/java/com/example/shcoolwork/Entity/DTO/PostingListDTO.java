package com.example.shcoolwork.Entity.DTO;

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
public class PostingListDTO implements Serializable {
    private String sortType;
    private Integer categoryId;
    private LocalDateTime lastTime;

}
