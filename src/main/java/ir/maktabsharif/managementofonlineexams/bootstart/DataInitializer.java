package ir.maktabsharif.managementofonlineexams.bootstart;

import ir.maktabsharif.managementofonlineexams.model.enums.Role;
import ir.maktabsharif.managementofonlineexams.model.enums.UserStatus;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(UserRepository userRepository) {
                return args -> {

            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                User manager = new User();
                manager.setEmail("admin@admin.com");
                manager.setPassword(passwordEncoder.encode("12345"));
                manager.setRole(Role.valueOf("ADMIN"));
                manager.setStatus(UserStatus.valueOf("APPROVED"));
                userRepository.save(manager);
            }
        };
    }
}
