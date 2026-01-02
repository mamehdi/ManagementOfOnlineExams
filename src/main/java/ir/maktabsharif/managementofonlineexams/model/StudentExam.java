package ir.maktabsharif.managementofonlineexams.model;

import ir.maktabsharif.managementofonlineexams.model.base.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_exams")
public class StudentExam extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private Integer score;
    private boolean completed;
}
