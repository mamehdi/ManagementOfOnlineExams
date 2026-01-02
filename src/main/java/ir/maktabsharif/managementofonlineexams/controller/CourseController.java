package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.dto.CourseDto;
import ir.maktabsharif.managementofonlineexams.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/list")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAllForAdmin());
        return "courses/courses_list";
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "courses/course_new";
    }

    @PostMapping
    public String create(@Valid CourseDto dto, BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "courses/course_new";
        }
        courseService.create(dto);
        return "redirect:/courses/list";
    }
}
