package com.example.ProectPract.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {
    private String avatarUrl;
    private Integer age;
    private String firstName;
    private String lastName;
    private String bio;
}