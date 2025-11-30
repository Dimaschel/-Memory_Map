package com.example.ProectPract.Service;

import com.example.ProectPract.DTO.TokenRefreshResponse;
import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Repository.UserRepository;
import com.example.ProectPract.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils tokenProvider;

    public TokenRefreshResponse refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh token is invalid!");
        }

        String username = tokenProvider.getUsernameFromToken(refreshToken);
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );

        String newAccessToken = tokenProvider.generateAccessToken(authentication);
        String newRefreshToken = tokenProvider.generateRefreshToken(authentication);

        return new TokenRefreshResponse(newAccessToken, newRefreshToken);
    }
}