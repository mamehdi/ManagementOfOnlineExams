package ir.maktabsharif.managementofonlineexams.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MCQ")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestion extends Question{
    @OneToMany(mappedBy = "question")
    private List<Option> options = new ArrayList<>();
}
