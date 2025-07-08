package com.socialapp.repository;

import com.socialapp.entity.UploadRequest;
import com.socialapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRequestRepository extends JpaRepository<UploadRequest, Long> {
    Optional<UploadRequest> findByUser(User user);
}

