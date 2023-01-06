package co.istad.admincambolen.features.post.service;

import org.springframework.stereotype.Service;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.utils.WebClientUtils;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final WebClientUtils webClientUtils;

    @Override
    public ApiResponse<?> fetchPosts(Long pageNum) {
       ApiResponse<?> response = webClientUtils.fetch(pageNum, "/posts");

        return response;
    }

    @Override
    public ApiResponse<?> deletePost(Long id) {
        ApiResponse<?> response = webClientUtils.delete("/posts",id);

        return response;
    }
    
}
