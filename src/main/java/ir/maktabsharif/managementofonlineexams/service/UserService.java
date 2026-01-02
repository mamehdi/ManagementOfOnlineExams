package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.dto.UserRegistrationDto;
import ir.maktabsharif.managementofonlineexams.dto.UserResponseDto;
import ir.maktabsharif.managementofonlineexams.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponseDto register(UserRegistrationDto dto);
    List<UserResponseDto> listUsers();
    UserResponseDto approveUser(Long id);
    Page<UserResponseDto> searchUsers(String firstName,String lastName,String email,
            Role role,
            Pageable pageable
    );
    void changeRole(Long userId, Role newRole);

}
