package com.example.ProectPract.DTO;

import lombok.Data;

@Data
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private String email;
    private String userType;

    public JwtResponse(String accessToken, String refreshToken, String email, String userType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.userType = userType;
    }
}