package ir.maktabsharif.managementofonlineexams.repository;

import ir.maktabsharif.managementofonlineexams.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> { }
