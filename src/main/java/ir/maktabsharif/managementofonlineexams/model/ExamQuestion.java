package ir.maktabsharif.managementofonlineexams.model;

import ir.maktabsharif.managementofonlineexams.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exam_questions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestion extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private Double defaultScore;
}
