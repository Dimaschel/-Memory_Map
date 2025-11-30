package com.example.ProectPract.Service;

import com.example.ProectPract.DTO.PostDto;
import com.example.ProectPract.DTO.PostMapper;
import com.example.ProectPract.DTO.PostResponseDto;
import com.example.ProectPract.DTO.PostUpdateDto;
import com.example.ProectPract.Entity.Post;
import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Repository.PostRepository;
import com.example.ProectPract.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.getOwner;


@RequiredArgsConstructor
@Transactional
@Service
public class PostService {
   private final PostRepository postRepository;
   private final PostMapper postMapper;


    public void createPost(PostDto postDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User) auth.getPrincipal();
        Post post = postMapper.toEntity(postDto, curUser);

        postRepository.save(post);

    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public List<PostResponseDto> getAllPosts() {
        List <Post> posts = postRepository.findAll();
        return posts.stream().map(postMapper::toDto).collect(Collectors.toList());
    }

    public void updatePost(Long id, PostUpdateDto updateDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (updateDto.getDescription() != null) {
            post.setDescription(updateDto.getDescription());
        }
        if (updateDto.getLocationLat() != null) {
            post.setLocationLat(updateDto.getLocationLat());
        }
        if (updateDto.getLocationLng() != null) {
            post.setLocationLng(updateDto.getLocationLng());
        }
        if (updateDto.getPhotoUrls() != null) {
            post.setPhotoUrls(updateDto.getPhotoUrls());
        }

        postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found with id: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User) auth.getPrincipal();

        if (!post.getUser().getId().equals(curUser.getId())) {
            throw new RuntimeException("Only owner can delete this post");
        }
        postRepository.deleteById(id);

    }
}
