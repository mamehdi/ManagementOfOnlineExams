package ir.maktabsharif.managementofonlineexams.dto;

import lombok.Data;

@Data
public class ExamResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer duration;

}
