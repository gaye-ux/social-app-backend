package com.socialapp.graphql;

import com.socialapp.entity.Users;
import com.socialapp.security.JwtUtil;
import com.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthResolver {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @MutationMapping
    public Map<String, Object> registerUser(@Argument String username,@Argument String phoneNo,@Argument String password) {
        Users user = Users.builder()
                .username(username)
                .phoneNo(phoneNo)
                .password(password)
                .build();
        Users savedUser = userService.register(user);
        String token = jwtUtil.generateToken(String.valueOf(savedUser.getPhoneNo()));

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", savedUser);
        return response;
    }

    @MutationMapping
    public Map<String, Object> login(@Argument String phoneNo,@Argument String password) {
        Users user = userService.findByPhoneNo(phoneNo)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        if (!passwordEncoder.matches(password, user.getPassword())){
//            throw new RuntimeException("Invalid credentials");
//        }

        // For testing purposes only
        if(!password.matches(user.getPassword())){
                  throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(String.valueOf(phoneNo));
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        return response;
    }
}

