package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto createQuestion(QuestionDto dto, Long teacherId);
    QuestionDto updateQuestion(QuestionDto dto, Long teacherId);
    List<QuestionDto> getBankQuestions(Long courseId, Long teacherId);
}
