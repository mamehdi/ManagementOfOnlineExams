package ir.maktabsharif.managementofonlineexams.dto;

import java.time.LocalDate;

public record CourseResponseDto(Long id,
                                String title,
                                LocalDate startDate,
                                LocalDate endDate,
                                String teacherFullName) {
}
