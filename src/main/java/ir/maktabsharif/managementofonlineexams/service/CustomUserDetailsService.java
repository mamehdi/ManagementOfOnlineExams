package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.config.CustomUserDetails;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
     //   Optional<User> user = userRepository.findByEmail(email);
        String string = String.valueOf(user.getStatus());
        if (string.isBlank()) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!string.equals("APPROVED")) {
            throw new UsernameNotFoundException("User not approved");
        }

return new CustomUserDetails(user);
        /*return  org.springframework.security.core.userdetails.User
                .withUsername(user.get().getEmail())
                .password(user.get().getPassword())
                .roles(String.valueOf(user.get().getRole()))
                .build();*/
    }
}
