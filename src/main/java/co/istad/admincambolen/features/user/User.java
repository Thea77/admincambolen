package co.istad.admincambolen.features.user;

import java.util.List;

import co.istad.admincambolen.features.post.model.File;
import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private File profile;
    private Boolean isEnabled;
    private String verificationCode;
    private List<Role> roles;
    private String resetToken;
}
