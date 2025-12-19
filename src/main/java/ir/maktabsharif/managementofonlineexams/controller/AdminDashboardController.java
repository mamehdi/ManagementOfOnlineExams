package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.enums.Role;
import ir.maktabsharif.managementofonlineexams.repository.CourseRepository;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {
        @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }


}
