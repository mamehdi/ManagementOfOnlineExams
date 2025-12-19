package ir.maktabsharif.managementofonlineexams.dto;

import ir.maktabsharif.managementofonlineexams.enums.Role;
import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
