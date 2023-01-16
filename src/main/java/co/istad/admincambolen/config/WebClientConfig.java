package co.istad.admincambolen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.features.auth.AuthServiceImpl;
import co.istad.admincambolen.features.auth.LoginResponse;
import co.istad.admincambolen.features.user.User;
import co.istad.admincambolen.utils.WebClientUtils;

@Configuration
public class WebClientConfig {
    
    @Value("${api.base-url}")
    private String apiBaseUrl;


    @Bean
    public WebClient webClient(){

        return WebClient.builder()
            .baseUrl(apiBaseUrl)
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjczODY3ODM2LCJpYXQiOjE2NzM4NDk4MzZ9._ZC-HcWmtZ9gt5R9T1GuayzRlKMSVrxKKMUaNyZfhAVYCchctIW11Y5K_v5WQFZxh4oYxF1IMUY0Ifv1PbkkkQ")
            .build();
    }
  
}
