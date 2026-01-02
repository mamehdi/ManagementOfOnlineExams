package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.dto.ExamQuestionScoreDto;
import ir.maktabsharif.managementofonlineexams.model.ExamQuestion;
import ir.maktabsharif.managementofonlineexams.repository.ExamQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamQuestionScoreService {
    private final ExamQuestionRepository examQuestionRepository;

    public List<ExamQuestionScoreDto> getExamQuestionsWithScores(Long examId) {
        List<ExamQuestion> examQuestions = examQuestionRepository.findByExamId(examId);
        return examQuestions.stream()
                .map(eq -> new ExamQuestionScoreDto(eq.getQuestion().getId(), eq.getQuestion().getTitle(), eq.getDefaultScore()))
                .collect(Collectors.toList());
    }

    public void updateQuestionScore(Long questionId, Double defaultScore) {
        ExamQuestion examQuestion = examQuestionRepository.findByQuestionId(questionId);
        examQuestion.setDefaultScore(defaultScore);
        examQuestionRepository.save(examQuestion);
    }

    public Double getTotalScore(Long examId) {
        return examQuestionRepository.findByExamId(examId).stream()
                .map(ExamQuestion::getDefaultScore)
                .filter(score -> score != null)
                .reduce(0d, Double::sum);
    }
}
