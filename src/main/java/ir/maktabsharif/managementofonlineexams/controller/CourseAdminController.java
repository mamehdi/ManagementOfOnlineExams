package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.enums.Role;
import ir.maktabsharif.managementofonlineexams.repository.CourseRepository;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses")
@RequiredArgsConstructor
public class CourseAdminController {

    private final CourseService courseService;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "admin/courses_list";
    }
    @GetMapping("/{id}/manage")
    public String manageCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseRepository.findById(id).orElseThrow());
        model.addAttribute("teachers", userRepository.findByRole(Role.TEACHER));
        model.addAttribute("students", userRepository.findByRole(Role.STUDENT));
        return "admin/course_manage";
    }

    @PostMapping("/{id}/assign-teacher")
    public String assignTeacher(@PathVariable Long id,
                                @RequestParam Long teacherId) {
        courseService.assignTeacher(id, teacherId);
        return "redirect:/admin/courses/" + id + "/manage";
    }

    @PostMapping("/{id}/add-student")
    public String addStudent(@PathVariable Long id,
                             @RequestParam Long studentId) {
        courseService.addStudent(id, studentId);
        return "redirect:/admin/courses/" + id + "/manage";
    }
}
