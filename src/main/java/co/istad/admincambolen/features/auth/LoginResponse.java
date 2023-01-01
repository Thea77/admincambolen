package co.istad.admincambolen.features.auth;

import java.util.List;

import co.istad.admincambolen.features.user.Role;
import co.istad.admincambolen.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class LoginResponse {

    private User user;
    private List<Role> roles;
    private String token;
    
}
