package ir.maktabsharif.managementofonlineexams.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ExamQuestionDto {
    @NotNull
    private Long examId;
    @NotNull
    private Long questionId;
    @NotNull
    @Positive
    private Double defaultScore;
    /*private String title;
    private String text;
    private String questionType;
    private List<OptionDto> options;*/

}
