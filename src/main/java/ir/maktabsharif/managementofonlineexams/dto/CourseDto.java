package ir.maktabsharif.managementofonlineexams.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
}
