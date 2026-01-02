package ir.maktabsharif.managementofonlineexams.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class ExamQuestionDto {
    @NotNull
    private Long examId;
    @NotNull
    private Long questionId;
    @NotNull
    @Positive
    private Double defaultScore;

}
