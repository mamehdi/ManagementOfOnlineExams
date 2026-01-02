package ir.maktabsharif.managementofonlineexams.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DESC")
public class DescriptiveQuestion extends Question{
    @Column(nullable = false)
    private String text;
}
