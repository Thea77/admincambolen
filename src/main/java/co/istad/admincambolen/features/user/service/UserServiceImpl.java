package co.istad.admincambolen.features.user.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.features.auth.LoginResponse;
import co.istad.admincambolen.features.file.model.File;
import co.istad.admincambolen.features.model.ApiResponse;
import co.istad.admincambolen.features.user.User;
import co.istad.admincambolen.features.user.web.CreateUserDto;
import co.istad.admincambolen.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final WebClientUtils webClientUtils;
    private final WebClient webClient;

    @Override
    public ApiResponse<?> fetchUsers() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();

        System.out.println("myAuth="+ customUserSecurity.getUser());
        

        ApiResponse<?> response = webClientUtils.fetchNoPagination("/users");
        return response;
    }

    @Override
    public ApiResponse<CreateUserDto> createUser(CreateUserDto body,MultipartFile file) throws JsonProcessingException {

        MultipartBodyBuilder filePart = new MultipartBodyBuilder();
        filePart.part("file", file.getResource());

        ApiResponse<File> fileResponse = webClient.post()
                            .uri("/files")
                            .contentType(MediaType.MULTIPART_FORM_DATA)
                            .body(BodyInserters.fromMultipartData(filePart.build()))
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<ApiResponse<File>>() {
                            }).block();
        
    //   System.out.println("fileUri="+fileResponse.getData());
        
      if(fileResponse.getData() == null){
        System.out.println("Please Insert Your Profile Image");
      
      }
      body.setProfileId(fileResponse.getData().getId());
      ApiResponse<CreateUserDto> response = webClientUtils.insert("/users/create-users", body);
      return response;   
     }



    @Override
    public ApiResponse<?> updateUserStatus(Long id, Boolean status) {

          ApiResponse<?> response = webClientUtils.update(id,"/user-status",status);

      return response;
    }

    @Override
    public ApiResponse<?> deleteUser(Long id) {

      ApiResponse<?> response = webClientUtils.delete("/users",id);

      return response;
    }

    @Override
    public ApiResponse<?> getUserProfile() {
     

      
      // ApiResponse<?> response = webClientUtils.getMe();

      // UserDto userDto = userMapper.fromModel(user);
      // userDto.getProfile().buildNameAndUri(fileBaseUri);

      return null;
    }
    
}
