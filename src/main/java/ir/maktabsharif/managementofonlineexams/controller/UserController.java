package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.dto.UserRegistrationDto;
import ir.maktabsharif.managementofonlineexams.dto.UserResponseDto;
import ir.maktabsharif.managementofonlineexams.model.enums.Role;
import ir.maktabsharif.managementofonlineexams.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto dto,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "users/register";
        }
        userService.register(dto);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/users_list";
    }

    @PostMapping("/{id}/approve")
    public String approve(@PathVariable Long id) {
        userService.approveUser(id);
        return "redirect:/users/list";
    }

    @GetMapping("/search")
    public String searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Role role,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").ascending());
        Page<UserResponseDto> resultPage = userService.searchUsers(firstName, lastName, email, role, pageable);

        model.addAttribute("usersPage", resultPage);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("role", role != null ? role.name() : "");
        return "users/users_search";
    }


}
