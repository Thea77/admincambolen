package co.istad.admincambolen.features.user.web;

import javax.validation.constraints.NotBlank;

import co.istad.admincambolen.validation.password.Password;
import co.istad.admincambolen.validation.password.PasswordMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@PasswordMatch(password = "newPassword", confirmedPassword = "confirmedPassword")
public class ChangePasswordDto {
    
    @NotBlank
    private String currentPassword;
    
    @Password
    @NotBlank
    private String newPassword;

    @Password
    @NotBlank
    private String confirmedPassword;
}
