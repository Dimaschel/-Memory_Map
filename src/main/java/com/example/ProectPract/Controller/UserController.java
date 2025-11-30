package com.example.ProectPract.Controller;

import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteId(Long id) {
        return ResponseEntity.ok(userService.deleteById(String.valueOf(id)));
    }

    @GetMapping("/getById/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


}