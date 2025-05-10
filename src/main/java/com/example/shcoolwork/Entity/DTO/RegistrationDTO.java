package com.example.shcoolwork.Entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDTO implements Serializable {

    private String username;

    private String studentId;

    private String phone;

    private String password;

    private String image;

    private Short compus;

    private Short enclosure;

    private Short unit;
}
