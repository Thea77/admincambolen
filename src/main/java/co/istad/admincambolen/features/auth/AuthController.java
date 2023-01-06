package co.istad.admincambolen.features.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authServiceImpl;
    
    @GetMapping("/login")
    String requestLoginView(){
        return "auth/login";
    }

    @PostMapping("/login")
    String doLogin(@RequestParam("username") String username,
                    @RequestParam("password") String password) throws JsonProcessingException{
        
      ApiResponse<?> response = authServiceImpl.loginUser(username, password);
       
        log.info("controllerRes= {}",response);

        return "redirect:/";
    }

}
