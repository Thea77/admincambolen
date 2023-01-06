package co.istad.admincambolen.features.post.web;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import co.istad.admincambolen.config.security.UserDetailsServiceImpl;
import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.post.model.Post;
import co.istad.admincambolen.features.post.service.PostServiceImpl;
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
    // private final WebClientUtils webClientUtils;
    private final PostServiceImpl postServiceImpl;

    @GetMapping("/post/tmpl/data")
    String requestPostTable(@RequestParam("pageNum") Long pageNum, ModelMap map){

        var response = postServiceImpl.fetchPosts(pageNum);
        map.addAttribute("data",response);
        return "post/table";
    }


}   
