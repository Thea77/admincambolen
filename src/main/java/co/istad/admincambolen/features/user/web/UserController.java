package co.istad.admincambolen.features.user.web;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.features.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public String getAllUsers(ModelMap map, @ModelAttribute("isSucceed") String isSucceed) {
        var response = userServiceImpl.fetchUsers();
        map.addAttribute("data", response);
        map.addAttribute("isSucceed", isSucceed);
        // System.out.println("SMG=" + isSucceed);

        return "user/user";
    }

    @GetMapping("/users-profile")
    public String getUsersProfile(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
       
        var response = userSecurity.getUser();
        map.addAttribute("data", response);
        System.out.println("profile=" + response);

        return "user/profile/profile";
    }

    @GetMapping("/users-create")
    public String creteUser(ModelMap map, CreateUserDto userDto) {
        map.addAttribute("userDto", userDto);

        return ("user/form");
    }

    @PostMapping(value = "/users-create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doCreteUser(CreateUserDto body,
            @PathParam("file") MultipartFile file,
            RedirectAttributes redirAttrs) throws JsonProcessingException {

        if (body != null) {
            redirAttrs.addFlashAttribute("isSucceed", "CREATED");
            userServiceImpl.createUser(body, file);

        }
        return ("redirect:/users");
    }

    @PostMapping("/user-status")
    public String doUpdateUserStatus(@RequestParam("id") Long id, @RequestParam("status") Boolean status) {

        userServiceImpl.updateUserStatus(id, status);
        return "redirect:/users";
    }

    @GetMapping("/users-delete/{id}")
    public String deletePost(@PathVariable("id") Long id, RedirectAttributes redirAttrs) {
        if (id != null) {
            redirAttrs.addFlashAttribute("isSucceed", "DELETED");
            userServiceImpl.deleteUser(id);
        }

        return "redirect:/users";
    }
}
