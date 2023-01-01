package co.istad.admincambolen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Value("${api.base-url}")
    private String apiBaseUrl;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
            .baseUrl(apiBaseUrl)
            .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2t1bnRoZWEiLCJleHAiOjE2NzI2MDQ2MzEsImlhdCI6MTY3MjU4NjYzMX0.Q-Umd6nNqHfd22CK4HopThAfeVz6DiJzTg1-VcLH9l61enUonnKsBKPfGaZmEf8nQaYw6O_Y7TgRcwf2GllJCw")
            .build();
    }
}
