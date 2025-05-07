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
public class Code implements Serializable {
    private Integer id;
    private String phone;
    private String code;

}
