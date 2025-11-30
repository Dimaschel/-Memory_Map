package com.example.ProectPract.Controller;

import com.example.ProectPract.DTO.PostDto;
import com.example.ProectPract.DTO.PostMapper;
import com.example.ProectPract.DTO.PostResponseDto;
import com.example.ProectPract.DTO.PostUpdateDto;
import com.example.ProectPract.Entity.Post;
import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Repository.PostRepository;
import com.example.ProectPract.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return ResponseEntity.ok("Post created");

    }

    @GetMapping("/get/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return postMapper.toDto(post);

    }

    @GetMapping("/getAll")
    public List<PostResponseDto> getAllPosts() {
       return postService.getAllPosts();
    }

    @PatchMapping("/change/{id}")
    public ResponseEntity<String> changePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {

        postService.updatePost(id, postUpdateDto);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

}
