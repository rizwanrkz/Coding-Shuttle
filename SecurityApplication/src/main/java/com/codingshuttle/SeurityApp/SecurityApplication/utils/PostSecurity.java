package com.codingshuttle.SeurityApp.SecurityApplication.utils;

import com.codingshuttle.SeurityApp.SecurityApplication.dto.PostDTO;
import com.codingshuttle.SeurityApp.SecurityApplication.entities.UserEntity;
import com.codingshuttle.SeurityApp.SecurityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

    public boolean isOwnerOfPost(Long postId) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());

    }
}
