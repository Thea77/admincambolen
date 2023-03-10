package co.istad.admincambolen.features.auth;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.model.ApiResponse;

public interface AuthService {

    ApiResponse<?> loginUser(String username, String password) throws JsonProcessingException;    

}
