package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.model.Course;
import ir.maktabsharif.managementofonlineexams.model.Exam;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.CourseRepository;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ExamService  examService;

    @GetMapping("/courses")
    public String listCourses(Authentication authentication, Model model) {
        String email = authentication.getName();
        User teacher = userRepository.findByEmail(email).orElseThrow();
        List<Course> courses = courseRepository.findAll()
                .stream()
                .filter(c -> c.getTeacher() != null && c.getTeacher().getId().equals(teacher.getId()))
                .toList();
        model.addAttribute("courses", courses);
        return "teacher/courses_list";
    }

    @GetMapping("/courses/{courseId}/exams")
    public String courseExams(@PathVariable Long courseId, Authentication authentication, Model model) {
        String email = authentication.getName();
        User teacher = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId).orElseThrow();
        List<Exam> exams = examService.findByCourse(courseId);

        model.addAttribute("course", course);
        model.addAttribute("exams", exams);
        model.addAttribute("currentUser", teacher);

        return "teacher/course_exams";
    }
}
