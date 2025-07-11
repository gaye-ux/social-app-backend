package com.socialapp.graphql;

import com.socialapp.entity.UploadRequest;
import com.socialapp.entity.Users;
import com.socialapp.repository.UploadRequestRepository;
import com.socialapp.repository.UserRepository;
import com.socialapp.service.UploadRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UploadRequestResolver {

    private final UploadRequestService uploadRequestService;

    private final UserRepository userRepository;

    private final UploadRequestRepository uploadRequestRepository;

    @MutationMapping
    public UploadRequest requestUploadAccess(@Argument Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return uploadRequestService.requestAccess(user);
    }

    @MutationMapping
    public UploadRequest approveUploadRequest(@Argument Long requestId) {
        UploadRequest request = uploadRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request for upload not found"));
        return uploadRequestService.approveRequest(request);
    }
}

