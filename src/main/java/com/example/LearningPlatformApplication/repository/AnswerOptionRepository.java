package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    List<AnswerOption> findByQuestionId(Long questionId);
    List<AnswerOption> findByQuestionQuizId(Long quizId);
    List<AnswerOption> findByIsCorrectTrueAndQuestionId(Long questionId);
}