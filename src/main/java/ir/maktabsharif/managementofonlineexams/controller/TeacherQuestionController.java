package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.config.CustomUserDetails;
import ir.maktabsharif.managementofonlineexams.dto.ExamQuestionDto;
import ir.maktabsharif.managementofonlineexams.dto.QuestionDto;
import ir.maktabsharif.managementofonlineexams.service.ExamQuestionService;
import ir.maktabsharif.managementofonlineexams.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher/courses/{courseId}/exams/{examId}")
public class TeacherQuestionController {
    private final QuestionService questionService;
    private final ExamQuestionService examQuestionService;

    private Long getCurrentTeacherId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails) auth.getPrincipal();
        return customUser.getUser().getId();
    }


    @GetMapping("/questions")
    public String showQuestions(@PathVariable Long courseId,
                                @PathVariable Long examId,
                                Model model,
                                @AuthenticationPrincipal CustomUserDetails currentUser) {
        Long teacherId = currentUser.getUser().getId();

        model.addAttribute("newQuestion", new QuestionDto());


        model.addAttribute("bankQuestions",
                questionService.getBankQuestions(courseId, teacherId));
        model.addAttribute("examQuestions",
                examQuestionService.listExamQuestions(examId));
        model.addAttribute("newQuestion", new QuestionDto());
        model.addAttribute("courseId", courseId);
        model.addAttribute("examId", examId);
        return "teacher/exam_questions";
    }

    @PostMapping("/questions/addExisting")
    public String addExistingQuestion(@RequestParam Long questionId,
                                      @RequestParam Double defaultScore,
                                      @PathVariable Long examId) {

        ExamQuestionDto dto = new ExamQuestionDto();
        dto.setExamId(examId);
        dto.setQuestionId(questionId);
        dto.setDefaultScore(defaultScore);

        examQuestionService.addQuestionToExam(dto);

        return "redirect:/teacher/courses/{courseId}/exams/{examId}/questions";
    }

    @PostMapping("/questions/addNew")
    public String addNewQuestion( @PathVariable Long courseId,
                                  @PathVariable Long examId,
                                  @ModelAttribute @Valid QuestionDto questionDto,
                                  @RequestParam("defaultScore") Double defaultScore,
                                  @AuthenticationPrincipal CustomUserDetails currentUser) {

        Long teacherId = currentUser.getUser().getId();

        QuestionDto created = questionService.createQuestion(questionDto, teacherId);

        if (created.getId() == null) {
            throw new IllegalStateException("Error saving question: ID is null");
        }
        ExamQuestionDto dto = new ExamQuestionDto();
        dto.setExamId(examId);
        dto.setQuestionId(created.getId());
        dto.setDefaultScore(defaultScore);

        examQuestionService.addQuestionToExam(dto);

        return "redirect:/teacher/courses/{courseId}/exams/{examId}/questions";
    }

}
