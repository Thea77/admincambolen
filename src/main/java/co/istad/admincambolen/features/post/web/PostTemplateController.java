package co.istad.admincambolen.features.post.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.post.model.Post;
import co.istad.admincambolen.utils.Pagination;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostTemplateController {

    // private final WebClient webClient; 
    private final WebClientUtils webClientUtils;

    @GetMapping("/tmpl/post/table")
    String requestPostTable(ModelMap map){

        var response = webClientUtils.fetch(1L, "posts");

        // ApiResponse<Pagination<Post>> response = webClient.get()
        //         .uri(builder 
        //         -> builder.path("posts")
        //             .queryParam("pageNum", 1)
        //             .queryParam("pageSize", 6)
        //             .build())
        //         .retrieve()
        //         .bodyToMono(new ParameterizedTypeReference<ApiResponse<Pagination<Post>>>() {})
        //         .block();
        //     // log.info("datass={}",response);
        map.addAttribute("response",response);

        return "post/table";
    }


}   
