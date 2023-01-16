package co.istad.admincambolen.features.user.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.features.auth.LoginResponse;
import co.istad.admincambolen.features.file.model.File;
import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.User;
import co.istad.admincambolen.features.user.web.ChangePasswordDto;
import co.istad.admincambolen.features.user.web.CreateUserDto;
import co.istad.admincambolen.features.user.web.UpdateUserDto;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final WebClientUtils webClientUtils;
  private final WebClient webClient;

  @Override
  public ApiResponse<?> fetchUsers() {

    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // CustomUserSecurity customUserSecurity = (CustomUserSecurity)
    // auth.getPrincipal();

    // System.out.println("(getUsers)myAuth="+ customUserSecurity.getUser());
    ApiResponse<?> response = webClientUtils.fetchNoPagination("/users");
    return response;
  }

  @Override
  public ApiResponse<CreateUserDto> createUser(CreateUserDto body, MultipartFile file) throws JsonProcessingException {

    MultipartBodyBuilder filePart = new MultipartBodyBuilder();
    filePart.part("file", file.getResource());

    ApiResponse<File> fileResponse = webClient.post()
        .uri("/files")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(filePart.build()))
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<ApiResponse<File>>() {
        }).block();

    // System.out.println("fileUri="+fileResponse.getData());

    if (fileResponse.getData() == null) {
      System.out.println("Please Insert Your Profile Image");

    }
    body.setProfileId(fileResponse.getData().getId());
    ApiResponse<CreateUserDto> response = webClientUtils.insert("/users/create-users", body);
    return response;
  }

  @Override
  public ApiResponse<?> updateUserStatus(Long id, Boolean status) {

    ApiResponse<?> response = webClientUtils.update(id, "/user-status", status);

    return response;
  }

  @Override
  public ApiResponse<?> deleteUser(Long id) {
    if (id != null) {
      ApiResponse<?> response = webClientUtils.delete("/users", id);
      return response;
    }
    return null;

  }

  @Override
  public ApiResponse<?> updateUserprofile(UpdateUserDto body) {
    if (body != null) {
      ApiResponse<?> response = webClientUtils.updateUserprofile("/edit-profile", body);
      // System.out.println("serviceUpdateProfile="+response);

      return response;
    }
    return null;
  }

  @Override
  public ApiResponse<?> updateProfileImage(MultipartFile file, Long id) {
    MultipartBodyBuilder filePart = new MultipartBodyBuilder();
    filePart.part("file", file.getResource());

    ApiResponse<File> fileResponse = webClient.put()
        .uri("/files/update/" + id)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(filePart.build()))
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<ApiResponse<File>>() {
        }).block();

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
    userSecurity.getUser().setProfile(fileResponse.getData());

    // System.out.println("fileUriUserSecurity="+userSecurity.getUser());

    return fileResponse;
  }

  @Override
  public ApiResponse<?> changePassword(ChangePasswordDto body) {
    ApiResponse<ChangePasswordDto> response = webClient.put()
          .uri("/auth/change-password")
          .bodyValue(body)
          .retrieve()
          .bodyToMono(new ParameterizedTypeReference<ApiResponse<ChangePasswordDto>>() {
          }).block();
   
    return response;
  }

}
