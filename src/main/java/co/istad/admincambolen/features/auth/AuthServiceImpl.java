package co.istad.admincambolen.features.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.User;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final WebClientUtils webClientUtils;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    public ApiResponse<?> login(String username, String password) throws JsonProcessingException {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);

        // System.out.println("bodyLogin="+ loginDto);
        //get User data
        ApiResponse<LoginResponse> response = webClientUtils.login("auth/login", loginDto);
        System.out.println("Res="+ response);

        LoginResponse userResponse = response.getData();

        Authentication authentication = daoAuthenticationProvider.authenticate(
            new UsernamePasswordAuthenticationToken(userResponse.getUser().getUsername(), loginDto));

      log.info("Aut={}",authentication);
      
        return response;
    }

    
}
