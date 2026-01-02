package ir.maktabsharif.managementofonlineexams.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CourseDto {

    private Long id;

    @NotBlank(message = "Course title cannot be blank")
    private String title;

    @NotNull(message = "Start date must be entered")
    @FutureOrPresent(message = "Start date must be today or later")
    private LocalDate startDate;

    @NotNull(message = "End date must be entered")
    @FutureOrPresent(message = "End date must be today or later")
    private LocalDate endDate;

    private Long teacherId;
}
