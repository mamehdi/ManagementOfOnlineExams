package ir.maktabsharif.managementofonlineexams.model;

import ir.maktabsharif.managementofonlineexams.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Question extends BaseModel <Long>{
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String text;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
