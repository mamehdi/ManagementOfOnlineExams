package ir.maktabsharif.managementofonlineexams.repository;

import ir.maktabsharif.managementofonlineexams.model.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {
    List<StudentExam> findByStudentId(Long studentId);
    List<StudentExam> findByExamId(Long examId);
}
