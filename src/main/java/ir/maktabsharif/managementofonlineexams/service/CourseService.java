package ir.maktabsharif.managementofonlineexams.service;

import ir.maktabsharif.managementofonlineexams.dto.CourseDto;
import ir.maktabsharif.managementofonlineexams.dto.CourseResponseDto;
import java.util.List;

public interface CourseService {
    CourseDto create(CourseDto dto);
    CourseDto assignTeacher(Long courseId, Long teacherId);
    void addStudent(Long courseId, Long studentId);
    List<CourseResponseDto> findAllForAdmin();


}
