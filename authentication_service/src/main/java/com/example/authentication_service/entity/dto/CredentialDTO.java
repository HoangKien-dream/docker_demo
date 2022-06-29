package com.example.authentication_service.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDTO {
    private String accessToken;
    private String refreshToken;
    private String username;
}
