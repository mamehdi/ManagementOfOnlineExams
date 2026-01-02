package ir.maktabsharif.managementofonlineexams.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamDto {
    private Long id;
    @NotBlank(message = "Exam title cannot be blank")
    private String title;
    @NotBlank(message = "Exam description is required")
    private String description;
    @NotNull(message = "Duration must be entered")
    @Min(value = 45, message = "Duration must be at least 45 minute")
    private Integer duration;
    private Long courseId;
}
