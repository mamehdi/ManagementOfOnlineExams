package ir.maktabsharif.managementofonlineexams.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    @NotBlank
    private String title;
    private String text;
    private String imagePath;
    private Long courseId;
    private Long examId;
    private String questionType;
    private List<OptionDto> options;

}
