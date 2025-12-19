package ir.maktabsharif.managementofonlineexams.mapper;

import ir.maktabsharif.managementofonlineexams.dto.UserRegistrationDto;
import ir.maktabsharif.managementofonlineexams.dto.UserResponseDto;
import ir.maktabsharif.managementofonlineexams.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRegistrationDto dto);
    UserResponseDto toResponse(User user);
}
