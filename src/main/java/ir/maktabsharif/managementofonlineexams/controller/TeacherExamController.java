package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.dto.ExamDto;
import ir.maktabsharif.managementofonlineexams.dto.ExamResponseDto;
import ir.maktabsharif.managementofonlineexams.model.Course;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.CourseService;
import ir.maktabsharif.managementofonlineexams.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher/courses/{courseId}/exams")
@RequiredArgsConstructor
public class TeacherExamController {
    private final ExamService examService;

    private final UserRepository userRepository;

    @GetMapping("/{examId}")
    public String examDetails(@PathVariable Long courseId,
                              @PathVariable Long examId,
                              Authentication auth,
                              Model model) {
        String email = auth.getName();
        User teacher = userRepository.findByEmail(email).orElseThrow();

        ExamResponseDto exam =
                examService.getExamForTeacher(examId, teacher.getId());


        model.addAttribute("exam", exam);
        model.addAttribute("courseId", courseId);

        return "teacher/exams_list";
    }


    @GetMapping("/new")
    public String newExam(@PathVariable Long courseId, Model model) {
        model.addAttribute("exam", new ExamDto());
        model.addAttribute("courseId", courseId);
        return "teacher/exam_new";
    }

    @PostMapping("/new")
    public String createExam(@PathVariable Long courseId, @Valid @ModelAttribute ExamDto dto,
                             BindingResult result,
                             Authentication auth,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("exam", dto);
            model.addAttribute("courseId", courseId);
            model.addAttribute("errors", result.getAllErrors());
            return "teacher/exam_new";
        }
        String username = auth.getName();
        User teacher = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        dto.setCourseId(courseId);
        examService.createExam(dto, teacher.getId());
        return "redirect:/teacher/courses/" + courseId + "/exams";
    }

    @GetMapping("/{examId}/edit")
    public String editExam(@PathVariable Long courseId, @PathVariable Long examId, Model model) {
        ExamDto examDto = new ExamDto();
        examDto.setId(examId);
        model.addAttribute("exam", examDto);
        model.addAttribute("courseId", courseId);
        return "teacher/exam_edit";
    }

    @PostMapping("/{examId}/update")
    public String updateExam(@PathVariable Long courseId, @PathVariable Long examId,
                             @Valid @ModelAttribute ExamDto dto,
                             BindingResult result,
                             Authentication auth,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("exam", dto);
            model.addAttribute("courseId", courseId);
            model.addAttribute("errors", result.getAllErrors());
            return "teacher/exam_edit";
        }
        String username = auth.getName();
        User teacher = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        dto.setId(examId);
        dto.setCourseId(courseId);
        examService.updateExam(dto, teacher.getId());
        return "redirect:/teacher/courses/" + courseId + "/exams";
    }

    @PostMapping("/{examId}/delete")
    public String deleteExam(@PathVariable Long courseId, @PathVariable Long examId, Authentication auth) {
        String username = auth.getName();
        User teacher = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        examService.deleteExam(examId, teacher.getId());
        return "redirect:/teacher/courses/" + courseId + "/exams";
    }
}
