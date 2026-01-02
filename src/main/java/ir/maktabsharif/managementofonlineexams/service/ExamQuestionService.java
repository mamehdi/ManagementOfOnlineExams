package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.dto.ExamQuestionDto;

import java.util.List;

public interface ExamQuestionService {
    void addQuestionToExam(ExamQuestionDto dto);
    List<ExamQuestionDto> listExamQuestions(Long examId);
    Double calculateTotalScore(Long examId);
}
