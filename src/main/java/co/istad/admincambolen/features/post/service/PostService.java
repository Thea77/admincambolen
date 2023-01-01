package co.istad.admincambolen.features.post.service;

import co.istad.admincambolen.features.model.ApiResponse;

public interface PostService {
    ApiResponse<?> fetchPosts();
}

