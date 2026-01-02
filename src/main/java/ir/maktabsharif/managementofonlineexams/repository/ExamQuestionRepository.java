package ir.maktabsharif.managementofonlineexams.repository;

import ir.maktabsharif.managementofonlineexams.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Long> {
    List<ExamQuestion> findByExamId(Long examId);

    ExamQuestion findByQuestionId(Long questionId);
}
