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
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2t1bnRoZWEiLCJleHAiOjE2NzM1MjI3MDksImlhdCI6MTY3MzUwNDcwOX0.yWAuC8R3wqm8Hfg3nht07hi8rTBsW12yKCcYBvXbgMcAvrIr-r_eHplC6ak2kIClCPvFaZ-UtgSTv4ppH9OMpA")
            .build();
    }
  
}
