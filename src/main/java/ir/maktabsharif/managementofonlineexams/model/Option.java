package ir.maktabsharif.managementofonlineexams.model;

import ir.maktabsharif.managementofonlineexams.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question_options")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Option extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "question_id")
    private MultipleChoiceQuestion question;

    @Column(nullable = false)
    private String text;

    private boolean correct;
}
