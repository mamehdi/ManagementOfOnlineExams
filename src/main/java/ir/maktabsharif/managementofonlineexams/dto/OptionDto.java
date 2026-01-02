package ir.maktabsharif.managementofonlineexams.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OptionDto {
    @NotBlank
    private String text;

    private boolean correct;
}
