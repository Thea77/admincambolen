package co.istad.admincambolen.features.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.config.security.UserDetailsServiceImpl;
import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.User;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final WebClientUtils clientUtils;

    @GetMapping("/")
    public String index(Model model) {

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // CustomUserSecurity customUserSecurity = (CustomUserSecurity)
        // auth.getPrincipal();

        // User user = customUserSecurity.getUser();
        // System.out.println("Principle="+ user);

        // model.addAttribute("user", user);
        // model.addAttribute("view", "fragments/header");

        return "home/home";
    }
}
