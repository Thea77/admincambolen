package co.istad.admincambolen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.config.security.UserDetailsServiceImpl;
import co.istad.admincambolen.features.auth.AuthServiceImpl;
import co.istad.admincambolen.features.post.service.PostServiceImpl;

@SpringBootTest
class AdmincambolenApplicationTests {

	@Autowired
	private AuthServiceImpl impl;
	@Test
	void contextLoads() throws JsonProcessingException {
	
			System.out.println(impl.login("sokunthea","#Thea2023"));
	
	}

}
