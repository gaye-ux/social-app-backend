package com.socialapp.service;

import com.socialapp.entity.UploadRequest;
import com.socialapp.entity.User;
import com.socialapp.repository.UploadRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadRequestService {
    private final UploadRequestRepository uploadRequestRepository;

    public UploadRequest requestAccess(User user) {
        UploadRequest req = UploadRequest.builder()
                .user(user)
                .status("PENDING")
                .requestedAt(LocalDateTime.now())
                .build();
        return uploadRequestRepository.save(req);
    }

    public Optional<UploadRequest> findByUser(User user) {
        return uploadRequestRepository.findByUser(user);
    }

    public UploadRequest approveRequest(UploadRequest request) {
        request.setStatus("APPROVED");
        return uploadRequestRepository.save(request);
    }
}
