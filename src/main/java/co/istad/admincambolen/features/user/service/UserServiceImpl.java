package co.istad.admincambolen.features.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.features.auth.LoginResponse;
import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.User;
import co.istad.admincambolen.features.user.web.CreateUserDto;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final WebClientUtils webClientUtils;

    @Override
    public ApiResponse<?> fetchUsers() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();

        System.out.println("myAuth="+ customUserSecurity.getUser());
        

        ApiResponse<?> response = webClientUtils.fetchNoPagination("/users");
        return response;
    }

    @Override
    public ApiResponse<CreateUserDto> createUser(CreateUserDto body) throws JsonProcessingException {
        ApiResponse<CreateUserDto> response = webClientUtils.insert("/users/create-users", body);
        
        System.out.println("bodyInsert="+ response);

        return null;
    }
    
}
