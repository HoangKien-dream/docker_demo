package com.example.authentication_service.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    private String thumbnail;
    private int gender;
    private String birthday;
    private String phone;
    private String address;
    private String email;
}
