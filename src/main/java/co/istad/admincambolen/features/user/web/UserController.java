package co.istad.admincambolen.features.user.web;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.istad.admincambolen.config.security.CustomUserSecurity;
import co.istad.admincambolen.features.file.model.File;
import co.istad.admincambolen.features.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Get All Users
     * 
     * @param map
     * @param isSucceed
     * @return
     */
    @GetMapping("/users")
    public String getAllUsers(ModelMap map, @ModelAttribute("isSucceed") String isSucceed) {
        var response = userServiceImpl.fetchUsers();
        map.addAttribute("data", response);
        map.addAttribute("isSucceed", isSucceed);
        // System.out.println("SMG=" + isSucceed);

        return "user/user";
    }

    /**
     * get User Profile
     * 
     * @param map
     * @param userDto
     * @param isSucceed
     * @return
     */
    @GetMapping("/users-profile")
    public String getUsersProfile(ModelMap map, UpdateUserDto userDto, @ModelAttribute("isSucceed") String isSucceed) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();

        var response = userSecurity.getUser();
        map.addAttribute("data", response);
        map.addAttribute("userDto", userDto);
        map.addAttribute("isSucceed", isSucceed);
        // System.out.println("profile=" + response);

        return "user/profile/profile";
    }

    /**
     * update user profile
     * 
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/users-profile")
    public String doUpdateUserprofile(UpdateUserDto body, RedirectAttributes redirAttrs) {
        if (body != null) {
            // System.out.println("bodyProfile="+body);
            userServiceImpl.updateUserprofile(body);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
            userSecurity.getUser().setFamilyName(body.getFamilyName());
            userSecurity.getUser().setGivenName(body.getGivenName());
            userSecurity.getUser().setPhoneNumber(body.getPhoneNumber());
            userSecurity.getUser().setEmail(body.getEmail());

            redirAttrs.addFlashAttribute("isSucceed", "UPDATED");
        }

        return "redirect:/users-profile";
    }

    /**
     * Get change password
     * 
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/users-change-password")
    public String changePassword(Model map, @ModelAttribute("isSucceed") String isSucceed) {
        map.addAttribute("passwordDto", new ChangePasswordDto());
        map.addAttribute("isSucceed", isSucceed);
        return "user/profile/change-password";
    }

    /**
     * change password
     * 
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/users-change-password")
    public String doChangePassword(@Valid @ModelAttribute("passwordDto") ChangePasswordDto passwordDto,
            BindingResult result, Model model, RedirectAttributes redirAttrs) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity customUserSecurity = (CustomUserSecurity) auth.getPrincipal();

        if (!bCryptPasswordEncoder.matches(passwordDto.getCurrentPassword(), customUserSecurity.getPassword())) {
            model.addAttribute("notMatch", "Current password is wrong!");
            // System.out.println("Password not Matched");
            return "user/profile/change-password";
        }

        if (result.hasErrors()) {
            // System.out.println("Password="+model.asMap());
            return "user/profile/change-password";
        } else {
            userServiceImpl.changePassword(passwordDto);
            String encodedPassword = bCryptPasswordEncoder.encode(passwordDto.getNewPassword());
            customUserSecurity.getUser().setPassword(encodedPassword);
            redirAttrs.addFlashAttribute("isSucceed", "CHANGEPASS");
            return "redirect:/users-profile";
        }

    }

    /**
     * update user isenabled
     * 
     * @param id
     * @param status
     * @return
     */
    @PostMapping(value = "/users-change-profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doUpdateUserProfileImage(@PathParam("file") MultipartFile file, Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserSecurity userSecurity = (CustomUserSecurity) auth.getPrincipal();
        id = userSecurity.getUser().getProfile().getId();
        // System.out.println("ImageId="+id);
        userServiceImpl.updateProfileImage(file, id);

        return "redirect:/users-profile";
    }

    /**
     * Create User(Get)
     * 
     * @param map
     * @param userDto
     * @return
     */
    @GetMapping("/users-create")
    public String creteUser(Model map) {
        map.addAttribute("userDto", new CreateUserDto());

        return ("user/form");
    }

    /**
     * Do Create User
     * 
     * @param body
     * @param file
     * @param redirAttrs
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/users-create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doCreteUser(@Valid @ModelAttribute("userDto") CreateUserDto userDto, 
            BindingResult result, @PathParam("file") MultipartFile file,
            RedirectAttributes redirAttrs,
            Model model) throws JsonProcessingException {

        if (result.hasErrors()) {
            System.out.println("Password=" + model.asMap());
            return ("user/form");
        } else {
            System.out.println("no error");
            redirAttrs.addFlashAttribute("isSucceed", "CREATED");
            userServiceImpl.createUser(userDto, file);
            return ("redirect:/users");
        }
        
    }

    /**
     * update user isenabled
     * 
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/user-status")
    public String doUpdateUserStatus(@RequestParam("id") Long id, @RequestParam("status") Boolean status) {

        userServiceImpl.updateUserStatus(id, status);
        return "redirect:/users";
    }

    /**
     * Delete User
     * 
     * @param id
     * @param redirAttrs
     * @return
     */
    @GetMapping("/users-delete/{id}")
    public String deletePost(@PathVariable("id") Long id, RedirectAttributes redirAttrs) {
        if (id != null) {
            redirAttrs.addFlashAttribute("isSucceed", "DELETED");
            userServiceImpl.deleteUser(id);
        }
        return "redirect:/users";
    }
}
