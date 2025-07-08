package com.socialapp.graphql;

import com.socialapp.entity.UploadRequest;
import com.socialapp.entity.User;
import com.socialapp.service.UploadRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UploadRequestResolver {

    private final UploadRequestService uploadRequestService;

    @MutationMapping
    public UploadRequest requestUploadAccess(Long userId) {
        return uploadRequestService.requestAccess(User.builder().id(userId).build());
    }

    @MutationMapping
    public UploadRequest approveUploadRequest(Long requestId) {
        UploadRequest request = UploadRequest.builder().id(requestId).build();
        request.setStatus("APPROVED");
        return uploadRequestService.approveRequest(request);
    }
}

