package com.socialapp.service;

import com.socialapp.entity.Users;
import com.socialapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public Optional<Users> findByPhoneNo(String phoneNo) {
        return userRepository.findByPhoneNo(phoneNo);
    }

    public List<Users> getAllUsers(){ return userRepository.findAll(); }

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setCanUpload(true);
        return userRepository.save(user);
    }

    public Users save(Users user) {
        return userRepository.save(user);
    }
}

