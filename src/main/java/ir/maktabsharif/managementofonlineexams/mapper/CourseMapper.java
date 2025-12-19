package ir.maktabsharif.managementofonlineexams.mapper;

import ir.maktabsharif.managementofonlineexams.dto.CourseDto;
import ir.maktabsharif.managementofonlineexams.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDto dto);
    CourseDto toDto(Course course);
}
