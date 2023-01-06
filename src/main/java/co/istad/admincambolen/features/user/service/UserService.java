package co.istad.admincambolen.features.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.web.CreateUserDto;

public interface UserService {
    
    /**
     * get all users
     * @return
     */
    ApiResponse<?> fetchUsers();

    ApiResponse<?> createUser(CreateUserDto body) throws JsonProcessingException;


}
