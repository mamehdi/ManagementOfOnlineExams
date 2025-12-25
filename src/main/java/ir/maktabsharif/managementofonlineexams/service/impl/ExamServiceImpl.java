package ir.maktabsharif.managementofonlineexams.service.impl;

import ir.maktabsharif.managementofonlineexams.dto.ExamDto;
import ir.maktabsharif.managementofonlineexams.dto.ExamResponseDto;
import ir.maktabsharif.managementofonlineexams.mapper.ExamMapper;
import ir.maktabsharif.managementofonlineexams.model.Course;
import ir.maktabsharif.managementofonlineexams.model.Exam;
import ir.maktabsharif.managementofonlineexams.model.User;
import ir.maktabsharif.managementofonlineexams.repository.CourseRepository;
import ir.maktabsharif.managementofonlineexams.repository.ExamRepository;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ExamMapper examMapper;

    @Override
    public List<ExamResponseDto> getExamsByCourseForTeacher(Long courseId, Long teacherId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        User teacher = userRepository.findById(teacherId).orElseThrow();

        return examRepository.findByCourseAndTeacher(course, teacher)
                .stream()
                .map(examMapper::toResponseDto)
                .toList();
    }


    @Override
    public ExamResponseDto createExam(ExamDto dto, Long teacherId) {
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow();
        User teacher = userRepository.findById(teacherId).orElseThrow();

        Exam exam = examMapper.toEntity(dto);
        exam.setTeacher(teacher);
        exam.setCourse(course);

        return examMapper.toResponseDto(examRepository.save(exam));
    }

    @Override
    public ExamResponseDto updateExam(ExamDto dto, Long teacherId) {
        Exam exam = examRepository.findById(dto.getId()).orElseThrow();
        if (!exam.getTeacher().getId().equals(teacherId)) {
            throw new IllegalStateException("You not permitted to update this exam");
        }

        exam.setTitle(dto.getTitle());
        exam.setDescription(dto.getDescription());
        exam.setDuration(dto.getDuration());

        return examMapper.toResponseDto(examRepository.save(exam));
    }

    @Override
    public void deleteExam(Long examId, Long teacherId) {
        Exam exam = examRepository.findById(examId).orElseThrow();
        if (!exam.getTeacher().getId().equals(teacherId)) {
            throw new IllegalStateException("You not permitted to delete this exam");
        }
        examRepository.delete(exam);
    }
    @Override
    public ExamResponseDto getExamForTeacher(Long examId, Long teacherId) {

        Exam exam = examRepository
                .findByIdAndCourse_Teacher_Id(examId, teacherId)
                .orElseThrow(() ->
                        new IllegalStateException("Acces denied for this exam"));
        return examMapper.toResponseDto(exam);
    }
    public List<Exam> findByCourse(Long courseId){
        return examRepository.findByCourseId(courseId);

    }
}
