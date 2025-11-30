package com.example.ProectPract.Controller;

import com.example.ProectPract.DTO.*;
import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Entity.UserDetailImpl;
import com.example.ProectPract.Repository.UserRepository;
import com.example.ProectPract.Security.JwtUtils;
import com.example.ProectPract.Service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;
    private final UserDetailImpl userDetailImpl;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateAccessToken(authentication);
            String refreshToken = jwtUtils.generateRefreshToken(authentication);

            User user = (User) authentication.getPrincipal();


            return ResponseEntity.ok(new JwtResponse(jwt, refreshToken, user.getEmail(), user.getUserType().name()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            TokenRefreshResponse response = refreshTokenService.refreshToken(request.getRefreshToken());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }

        if (signUpRequest.getUserType() == null) {
            return ResponseEntity.badRequest().body("Error: User type is required!");
        }

        try {
            User user = new User();
            user.setEmail(signUpRequest.getEmail());
            user.setUserType(signUpRequest.getUserType());
            user.setPassword(encoder.encode(signUpRequest.getPassword()));

            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: Registration failed!");
        }
    }

}
