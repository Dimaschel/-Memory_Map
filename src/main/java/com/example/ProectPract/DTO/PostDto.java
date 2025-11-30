package com.example.ProectPract.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private String description;

    private Double locationLat;

    private Double locationLng;

    private List<String> photoUrls = new ArrayList<>();
}
