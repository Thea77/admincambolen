package co.istad.admincambolen.features.post.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.post.model.Post;
import co.istad.admincambolen.features.post.service.PostServiceImpl;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final WebClientUtils webClientUtils;
    private final PostServiceImpl postServiceImpl;

    @GetMapping("/posts")
    public String getAllPosts(ModelMap map){
        return "post/post";
    }


    @GetMapping("/posts-delete/{id}")
    public String deletePost(@PathVariable("id") Long id){
        
            postServiceImpl.deletePost(id);
       
        return "redirect:/posts";
    }
}
