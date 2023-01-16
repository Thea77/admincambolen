package co.istad.admincambolen.features.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.web.ChangePasswordDto;
import co.istad.admincambolen.features.user.web.CreateUserDto;
import co.istad.admincambolen.features.user.web.UpdateUserDto;

public interface UserService {
    
    /**
     * get all users
     * @return
     */
    ApiResponse<?> fetchUsers();

    /**
     * create User
     * @param body
     * @param file
     * @return
     * @throws JsonProcessingException
     */
    ApiResponse<?> createUser(CreateUserDto body,MultipartFile file) throws JsonProcessingException;

    /**
     * update or Ban User
     * @param id
     * @param status
     * @return
     */
    ApiResponse<?> updateUserStatus(Long id, Boolean status);


    /**
     * delete user
     * @param id
     * @return
     */
    ApiResponse<?> deleteUser(Long id);

    /**
     * change user profile picture
     * @param file
     * @param id
     * @return
     */
    ApiResponse<?> updateProfileImage(MultipartFile file, Long id);


    /**
     * edit user information
     * @param body
     * @return
     */
    ApiResponse<?> updateUserprofile(UpdateUserDto body);


    /**
     * change password
     * @param body
     * @return
     */
    ApiResponse<?> changePassword(ChangePasswordDto body);




}
