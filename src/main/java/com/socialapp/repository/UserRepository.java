package com.socialapp.repository;

import com.socialapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByPhoneNo(String phoneNo);



}

