package co.istad.admincambolen.features.user.web;

import java.util.List;

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
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private String confirmedPassword;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private Long profileId;
    private List<Integer> roleIds;
}
