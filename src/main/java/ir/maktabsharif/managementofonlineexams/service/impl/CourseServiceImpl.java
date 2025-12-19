package ir.maktabsharif.managementofonlineexams.service.impl;

import ir.maktabsharif.managementofonlineexams.dto.CourseDto;
import ir.maktabsharif.managementofonlineexams.dto.CourseResponseDto;
import ir.maktabsharif.managementofonlineexams.enums.Role;
import ir.maktabsharif.managementofonlineexams.enums.UserStatus;
import ir.maktabsharif.managementofonlineexams.mapper.CourseMapper;
import ir.maktabsharif.managementofonlineexams.model.Course;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.CourseRepository;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto create(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);
        return courseMapper.toDto(courseRepository.save(course));
    }



    @Override
    public CourseDto assignTeacher(Long courseId, Long teacherId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        User teacher = userRepository.findById(teacherId).orElseThrow();
        if (teacher.getRole() != Role.TEACHER) {
            throw new IllegalStateException("This user is not a teacher");
        }
        if (teacher.getStatus() != UserStatus.APPROVED) {
            throw new IllegalStateException("Teacher is not approved");
        }

        course.setTeacher(teacher);
        return courseMapper.toDto(courseRepository.save(course));
    }

    public void addStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow();

        User student = userRepository.findById(studentId)
                .orElseThrow();

        if (student.getRole() != Role.STUDENT) {
            throw new IllegalStateException("This user is not a student");
        }
        if (student.getStatus() != UserStatus.APPROVED) {
            throw new IllegalStateException("Student is not approved");
        }
        course.getStudents().add(student);
        courseRepository.save(course);

    }

    @Override
    public List<CourseResponseDto> findAllForAdmin() {
        return courseRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private CourseResponseDto toDto(Course course) {

        String teacherName = "—";
        User teacher = course.getTeacher();

        if (teacher != null) {
            teacherName = userRepository.findById(course.getTeacher().Id())
                    .map(u -> u.getFirstName() + " " + u.getLastName())
                    .orElse("—");
        }

        return new CourseResponseDto(
                course.getId(),
                course.getTitle(),
                course.getStartDate(),
                course.getEndDate(),
                teacherName
        );
    }
}
