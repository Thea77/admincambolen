package co.istad.admincambolen.features.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public String getAllUsers(ModelMap map){
       var response = userServiceImpl.fetchUsers();
        map.addAttribute("data",response);
        
        return "user/user";
    }

    @GetMapping("/users-create")
    public String creteUser(ModelMap map, CreateUserDto userDto){
        map.addAttribute("userDto", userDto);
        
        return ("user/form");
    }

    @PostMapping("/users-create")
    public String doCreteUser(CreateUserDto body) throws JsonProcessingException{
        body.setProfileId(3L);
        var response = userServiceImpl.createUser(body);

        System.out.println(response.getMessage());
        System.out.println("post="+body);
        return "redirect:/users";
    }
}
