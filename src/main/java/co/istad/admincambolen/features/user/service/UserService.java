package co.istad.admincambolen.features.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.web.CreateUserDto;

public interface UserService {
    
    /**
     * get all users
     * @return
     */
    ApiResponse<?> fetchUsers();

    ApiResponse<?> createUser(CreateUserDto body,MultipartFile file) throws JsonProcessingException;

    ApiResponse<?> updateUserStatus(Long id, Boolean status);

    ApiResponse<?> deleteUser(Long id);

    ApiResponse<?> getUserProfile();


}
