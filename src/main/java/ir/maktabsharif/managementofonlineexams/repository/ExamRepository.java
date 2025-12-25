package ir.maktabsharif.managementofonlineexams.repository;

import ir.maktabsharif.managementofonlineexams.model.Course;
import ir.maktabsharif.managementofonlineexams.model.Exam;
import ir.maktabsharif.managementofonlineexams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCourseAndTeacher(Course course, User teacher);
    Optional<Exam> findByIdAndCourse_Teacher_Id(Long examId, Long teacherId);
    List<Exam> findByCourseId(Long courseId);

}
