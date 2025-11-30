package com.example.ProectPract.Service;

import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String deleteUserByEmail(String email) {

        if(!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException("User not found");
        }

        userRepository.deleteByEmail(email);
        return "User deleted successfully";
    }
    public String deleteById(String id) {
        if(!userRepository.existsById(Long.valueOf(id))) {
            throw new UsernameNotFoundException("User not found");
        }
        userRepository.deleteById(Long.valueOf(id));
        return "User deleted successfully";

    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

}
