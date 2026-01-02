package ir.maktabsharif.managementofonlineexams.controller;

import ir.maktabsharif.managementofonlineexams.dto.ExamQuestionScoreDto;
import ir.maktabsharif.managementofonlineexams.service.ExamQuestionScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher/exams")
@RequiredArgsConstructor
@ResponseBody

public class TeacherQuestionScoreController {
    private final ExamQuestionScoreService scoreService;

    @GetMapping("/{examId}/questions-scores")
    public List<ExamQuestionScoreDto> getQuestionsScores(@PathVariable Long examId) {
        return scoreService.getExamQuestionsWithScores(examId);
    }

    @PostMapping("/questions/{questionId}/score")
    public void updateQuestionScore(@PathVariable Long questionId, @RequestParam Double score) {
        scoreService.updateQuestionScore(questionId, score);
    }

    @GetMapping("/{examId}/total-score")
    public Double getTotalScore(@PathVariable Long examId) {
        return scoreService.getTotalScore(examId);
    }
}
