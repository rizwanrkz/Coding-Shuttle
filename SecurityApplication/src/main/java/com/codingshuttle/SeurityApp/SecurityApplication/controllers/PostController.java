package com.codingshuttle.SeurityApp.SecurityApplication.controllers;

import com.codingshuttle.SeurityApp.SecurityApplication.dto.PostDTO;
import com.codingshuttle.SeurityApp.SecurityApplication.services.PostService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping
    @Secured("ROLE_USER")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

}

