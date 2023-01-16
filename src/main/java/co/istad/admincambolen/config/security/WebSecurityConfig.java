package co.istad.admincambolen.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/**/*.js", "/**/*.css", "/resources/**", "/static/**").permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated();
               
        http.formLogin()
            .loginPage("/login").permitAll()
            // .defaultSuccessUrl("/")
            // .failureUrl("/login")
            .successHandler(customAuthenticationSuccessHandler);

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint);

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

}
