package ir.maktabsharif.managementofonlineexams.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExamQuestionScoreDto {
    private Long questionId;
    private String questionTitle;
    private Double defaultScore;
}
