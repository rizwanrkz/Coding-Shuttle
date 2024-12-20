package com.codingshuttle.SeurityApp.SecurityApplication.services;

import com.codingshuttle.SeurityApp.SecurityApplication.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
