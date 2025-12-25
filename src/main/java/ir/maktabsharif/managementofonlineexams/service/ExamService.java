package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.dto.ExamDto;
import ir.maktabsharif.managementofonlineexams.dto.ExamResponseDto;
import ir.maktabsharif.managementofonlineexams.model.Exam;

import java.util.List;

public interface ExamService {
    List<ExamResponseDto> getExamsByCourseForTeacher(Long courseId, Long teacherId);
    ExamResponseDto createExam(ExamDto dto, Long teacherId);
    ExamResponseDto updateExam(ExamDto dto, Long teacherId);
    void deleteExam(Long examId, Long teacherId);
    ExamResponseDto getExamForTeacher(Long examId, Long teacherId);
    List<Exam> findByCourse(Long courseId);

}
