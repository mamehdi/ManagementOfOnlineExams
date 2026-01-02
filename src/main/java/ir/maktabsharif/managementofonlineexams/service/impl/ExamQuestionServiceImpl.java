package ir.maktabsharif.managementofonlineexams.service.impl;

import ir.maktabsharif.managementofonlineexams.dto.ExamQuestionDto;
import ir.maktabsharif.managementofonlineexams.model.Exam;
import ir.maktabsharif.managementofonlineexams.model.ExamQuestion;
import ir.maktabsharif.managementofonlineexams.model.Question;
import ir.maktabsharif.managementofonlineexams.repository.ExamQuestionRepository;
import ir.maktabsharif.managementofonlineexams.repository.ExamRepository;
import ir.maktabsharif.managementofonlineexams.repository.QuestionRepository;
import ir.maktabsharif.managementofonlineexams.service.ExamQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl implements ExamQuestionService {
    private final ExamQuestionRepository examQuestionRepository;
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;

    @Override
    public void addQuestionToExam(ExamQuestionDto dto) {
        if (dto.getExamId() == null || dto.getQuestionId() == null) {
            throw new IllegalArgumentException("******** ******** Exam ID or Question ID cannot be null");
        }

        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new IllegalArgumentException("******* ******* Exam not found"));

        Question question = questionRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("****** ******* Question not found"));

        ExamQuestion eq = new ExamQuestion();
        eq.setExam(exam);
        eq.setQuestion(question);
        eq.setDefaultScore(dto.getDefaultScore());

        examQuestionRepository.save(eq);
    }


    @Override
    public List<ExamQuestionDto> listExamQuestions(Long examId) {
        return examQuestionRepository.findByExamId(examId).stream().map(eq -> {
            ExamQuestionDto dto = new ExamQuestionDto();
            dto.setExamId(eq.getExam().getId());
            dto.setQuestionId(eq.getQuestion().getId());
            dto.setDefaultScore(eq.getDefaultScore());
            return dto;
        }).toList();
    }

    @Override
    public Double calculateTotalScore(Long examId) {
        return examQuestionRepository.findByExamId(examId).stream()
                .mapToDouble(ExamQuestion::getDefaultScore).sum();
    }
}
