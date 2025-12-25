package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final UserRepository  userRepository;
    @GetMapping("/dashboard")
    public String dashboard(Authentication auth,Model model,Principal principal) {


       UserDetails user = (UserDetails)  auth.getPrincipal();
        Optional<User> fullName = userRepository.findByEmail(principal.getName());
        model.addAttribute("userFullName", fullName.get().getFirstName() + " " + fullName.get().getLastName());

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
            return "redirect:/admin/dashboard";

        return "users/dashboard";
    }
}
