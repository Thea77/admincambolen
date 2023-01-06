package co.istad.admincambolen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.config.WebClientConfig;
import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.config.security.UserDetailsServiceImpl;
import co.istad.admincambolen.features.auth.AuthServiceImpl;
import co.istad.admincambolen.features.post.service.PostServiceImpl;
import co.istad.admincambolen.features.user.service.UserServiceImpl;
import co.istad.admincambolen.features.user.web.CreateUserDto;

@SpringBootTest
class AdmincambolenApplicationTests {

	@Autowired
	private AuthServiceImpl impl;
	private WebClientConfig config;
	private UserServiceImpl userServiceImpl;
	@Test
	void contextLoads()  throws IOException {
		// System.out.println(impl.loginUser("sokunthea","#Thea2023"));
		// System.out.println(config);
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();
        // CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();
		// CreateUserDto dto = new CreateUserDto();
		
		// dto.setUsername("abc");
		// dto.setEmail("abc@gmail.com");
		// dto.setFamilyName("aaa");
		// dto.setGivenName("bbb");
		// dto.setPhoneNumber("152535263");
		// dto.setProfileId(3L);
		// // dto.setRoleIds(Arrays.asList(1,2));
		// dto.setPassword("12345");
		// dto.setConfirmedPassword("12345");

        // System.out.println("myAuth="+ userServiceImpl.createUser(dto));
	
	}

}
