package ir.maktabsharif.managementofonlineexams.dto;

import lombok.Data;

@Data
public class ExamDto {
    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private Long courseId;
}
