package com.example.ProectPract.DTO;

import com.example.ProectPract.Entity.userType;
import lombok.Data;


@Data
public class SignupRequest {
    private String email;
    private String number;
    private userType userType;
    private String password;
}