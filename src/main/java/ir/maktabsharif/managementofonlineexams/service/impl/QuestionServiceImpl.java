package ir.maktabsharif.managementofonlineexams.service.impl;

import ir.maktabsharif.managementofonlineexams.dto.MultipleChoiceQuestionDto;
import ir.maktabsharif.managementofonlineexams.dto.OptionDto;
import ir.maktabsharif.managementofonlineexams.dto.QuestionDto;
import ir.maktabsharif.managementofonlineexams.model.*;
import ir.maktabsharif.managementofonlineexams.repository.CourseRepository;
import ir.maktabsharif.managementofonlineexams.repository.OptionRepository;
import ir.maktabsharif.managementofonlineexams.repository.QuestionRepository;
import ir.maktabsharif.managementofonlineexams.repository.UserRepository;
import ir.maktabsharif.managementofonlineexams.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;

    @Override
    public QuestionDto createQuestion(QuestionDto dto, Long teacherId) {
        User teacher = userRepository.findById(teacherId).orElseThrow(() ->
                new IllegalArgumentException("Teacher not found"));
   /*     if (dto.getCourseId() == null) {
            throw new IllegalArgumentException("courseId cannot be null when creating question");
        }*/
        Course course = courseRepository.findById(dto.getCourseId()).orElseThrow(()->
                new IllegalArgumentException("Course not found"));;


        Question question;
        if (dto.getOptions() != null && !dto.getOptions().isEmpty()) {
            MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
            BeanUtils.copyProperties(dto, mcq);
            mcq.setTeacher(teacher);
            mcq.setCourse(course);
            mcq = questionRepository.saveAndFlush(mcq);

            for (OptionDto o : dto.getOptions()) {
                Option option = new Option();
                option.setQuestion(mcq);
                option.setText(o.getText());
                option.setCorrect(o.isCorrect());
                mcq.getOptions().add(option);
                optionRepository.saveAndFlush(option);
            }
            question = questionRepository.saveAndFlush(mcq);
            question = mcq;

        } else {
            var desc = new DescriptiveQuestion();
            BeanUtils.copyProperties(dto, desc);
            desc.setTeacher(teacher);
            desc.setCourse(course);
            question = questionRepository.saveAndFlush(desc);
        }
        return mapToDto(question);
    }

    @Override
    public QuestionDto updateQuestion(QuestionDto dto, Long teacherId) {
        Question q = questionRepository.findById(dto.getId()).orElseThrow();
        if (!q.getTeacher().getId().equals(teacherId)) {
            throw new IllegalStateException("Not permitted");
        }
        q.setTitle(dto.getTitle());
        q.setText(dto.getText());
        questionRepository.save(q);
        return mapToDto(q);
    }

    @Override
    public List<QuestionDto> getBankQuestions(Long courseId, Long teacherId) {
        return questionRepository.findAll().stream()
                .filter(q -> q.getCourse().getId().equals(courseId)
                        && q.getTeacher().getId().equals(teacherId))
                .map(this::mapToDto)
                .toList();
    }

    private QuestionDto mapToDto(Question q) {
        if (q instanceof MultipleChoiceQuestion mcq) {
            MultipleChoiceQuestionDto dto = new MultipleChoiceQuestionDto();
            BeanUtils.copyProperties(q, dto);
            dto.setOptions(mcq.getOptions().stream().map(o -> {
                OptionDto od = new OptionDto();
                od.setText(o.getText());
                od.setCorrect(o.isCorrect());
                return od;
            }).toList());
            dto.setId(q.getId());
            return dto;
        }
        QuestionDto out = new QuestionDto();
        BeanUtils.copyProperties(q, out);
        out.setId(q.getId());
        return out;
    }
}
