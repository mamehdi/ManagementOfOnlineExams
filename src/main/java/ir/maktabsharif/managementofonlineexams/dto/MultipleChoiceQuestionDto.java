package ir.maktabsharif.managementofonlineexams.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)

public class MultipleChoiceQuestionDto extends QuestionDto {
    private List<OptionDto> options = new ArrayList<>();

}
