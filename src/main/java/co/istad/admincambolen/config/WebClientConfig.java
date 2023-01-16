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
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjczODkzODUzLCJpYXQiOjE2NzM4NzU4NTN9.ISP9gGKuygYU4mDdvyv-7PsFCJTclWpbl2u1eokQWP6j8up8ZLkwiOKwb1Lmfma4WEMGvBuHehQnokL2NEm1Hg")
            .build();
    }
  
}
