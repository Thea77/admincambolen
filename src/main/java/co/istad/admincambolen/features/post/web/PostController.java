package co.istad.admincambolen.features.post.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.post.model.Post;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final WebClient webClient;

    @GetMapping("/posts")
    public String getAllPosts(ModelMap map){

        // ApiResponse<Post> response = webClient.get()
        //     .uri("posts")
        //     .retrieve()
        //     .bodyToMono(new ParameterizedTypeReference<ApiResponse<Post>>(){})
        //     .block();
        //     map.addAttribute("posts", response);
        return "post/post";
    }

}
