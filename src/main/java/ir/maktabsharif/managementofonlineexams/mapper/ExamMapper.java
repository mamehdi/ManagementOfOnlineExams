package ir.maktabsharif.managementofonlineexams.mapper;

import ir.maktabsharif.managementofonlineexams.dto.ExamDto;
import ir.maktabsharif.managementofonlineexams.dto.ExamResponseDto;
import ir.maktabsharif.managementofonlineexams.model.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    Exam toEntity(ExamDto dto);
    ExamDto toDto(Exam exam);
    ExamResponseDto toResponseDto(Exam exam);
}
