package com.socialapp.graphql;

import com.socialapp.entity.Users;
import com.socialapp.security.JwtUtil;
import com.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthResolver {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @MutationMapping
    public Map<String, Object> registerUser(String username, String email, String password) {
        Users user = Users.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        Users savedUser = userService.register(user);
        String token = jwtUtil.generateToken(savedUser.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", savedUser);
        return response;
    }

    @MutationMapping
    public Map<String, Object> login(String email, String password) {
        Users user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // NOTE: In production, compare hashed password with encoder
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(email);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return response;
    }
}

