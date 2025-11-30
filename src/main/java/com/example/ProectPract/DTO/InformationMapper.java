package com.example.ProectPract.DTO;

import com.example.ProectPract.Entity.Information;
import com.example.ProectPract.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class InformationMapper {

    public Information toEntity(InformationDto dto, User user) {
        Information information = new Information();
        information.setAvatarUrl(dto.getAvatarUrl());
        information.setAge(dto.getAge());
        information.setFirstName(dto.getFirstName());
        information.setLastName(dto.getLastName());
        information.setBIO(dto.getBio());
        information.setUser(user);
        return information;
    }

    public InformationResponseDto toDto(Information information) {
        InformationResponseDto dto = new InformationResponseDto();
        dto.setId(information.getId());
        dto.setAvatarUrl(information.getAvatarUrl());
        dto.setAge(information.getAge());
        dto.setFirstName(information.getFirstName());
        dto.setLastName(information.getLastName());
        dto.setBio(information.getBIO());
        dto.setUserId(information.getUser().getId());
        return dto;
    }

    public void updateEntityFromDto(InformationDto dto, Information information) {
        if (dto.getAvatarUrl() != null) {
            information.setAvatarUrl(dto.getAvatarUrl());
        }
        if (dto.getAge() != null) {
            information.setAge(dto.getAge());
        }
        if (dto.getFirstName() != null) {
            information.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            information.setLastName(dto.getLastName());
        }
        if (dto.getBio() != null) {
            information.setBIO(dto.getBio());
        }
    }
}