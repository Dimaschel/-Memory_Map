package com.example.ProectPract.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String description;
    private Double locationLat;
    private Double locationLng;
    private LocalDateTime createdAt;

    private List<String> photoUrls = new ArrayList<>();

    private Long userId;

}