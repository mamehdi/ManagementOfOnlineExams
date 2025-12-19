package ir.maktabsharif.managementofonlineexams.service.impl;

import ir.maktabsharif.managementofonlineexams.dto.UserRegistrationDto;
import ir.maktabsharif.managementofonlineexams.dto.UserResponseDto;
import ir.maktabsharif.managementofonlineexams.enums.Role;
import ir.maktabsharif.managementofonlineexams.enums.UserStatus;
import ir.maktabsharif.managementofonlineexams.mapper.UserMapper;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.repository.specification.UserSpecification;
import ir.maktabsharif.managementofonlineexams.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationDto dto) {
        User user = userMapper.toUser(dto);
        user.setStatus(UserStatus.PENDING);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public List<UserResponseDto> listUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto approveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(UserStatus.APPROVED);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public Page<UserResponseDto> searchUsers(
            String firstName,
            String lastName,
            String email,
            Role role,
            Pageable pageable
    ) {

        Specification<User> spec = Specification
                .where(UserSpecification.hasRole(role))
                .and(UserSpecification.firstNameContains(firstName))
                .and(UserSpecification.lastNameContains(lastName))
                .and(UserSpecification.emailContains(email));

        Page<User> page = userRepository.findAll(spec, pageable);

        return page.map(userMapper::toResponse);
    }
    @Override
    public void changeRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        user.setRole(newRole);
        userRepository.save(user);
    }

}
