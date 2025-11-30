package com.example.ProectPract.DTO;

import com.example.ProectPract.Entity.Post;
import com.example.ProectPract.Entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PostMapper {

    public Post toEntity(PostDto dto, User user) {
        Post post = new Post();
        post.setDescription(dto.getDescription());
        post.setLocationLat(dto.getLocationLat());
        post.setLocationLng(dto.getLocationLng());
        post.setPhotoUrls(dto.getPhotoUrls() != null ? dto.getPhotoUrls() : new ArrayList<>());
        post.setUser(user);
        return post;
    }

    public Post toEntity(PostDto dto, Long userId) {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setDescription(dto.getDescription());
        post.setLocationLat(dto.getLocationLat());
        post.setLocationLng(dto.getLocationLng());
        post.setPhotoUrls(dto.getPhotoUrls() != null ? dto.getPhotoUrls() : new ArrayList<>());
        post.setUser(user);
        return post;
    }

    public PostResponseDto toDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setDescription(post.getDescription());
        dto.setLocationLat(post.getLocationLat());
        dto.setLocationLng(post.getLocationLng());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setPhotoUrls(post.getPhotoUrls() != null ? post.getPhotoUrls() : new ArrayList<>());
        dto.setUserId(post.getUser().getId());
        return dto;
    }
}
