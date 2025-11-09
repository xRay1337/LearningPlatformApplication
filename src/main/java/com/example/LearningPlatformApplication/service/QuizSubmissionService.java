package com.example.LearningPlatformApplication.service;

import com.example.LearningPlatformApplication.entity.QuizSubmission;
import com.example.LearningPlatformApplication.repository.QuizSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizSubmissionService {
    private final QuizSubmissionRepository quizSubmissionRepository;

    public QuizSubmission submitQuiz(QuizSubmission quizSubmission) {
        quizSubmission.setTakenAt(LocalDateTime.now());
        return quizSubmissionRepository.save(quizSubmission);
    }

    public Optional<QuizSubmission> getQuizSubmissionById(Long id) {
        return quizSubmissionRepository.findById(id);
    }

    public List<QuizSubmission> getQuizSubmissionsByQuiz(Long quizId) {
        return quizSubmissionRepository.findByQuizId(quizId);
    }

    public List<QuizSubmission> getQuizSubmissionsByStudent(Long studentId) {
        return quizSubmissionRepository.findByStudentId(studentId);
    }

    public Optional<QuizSubmission> getStudentQuizSubmission(Long quizId, Long studentId) {
        return quizSubmissionRepository.findByQuizIdAndStudentId(quizId, studentId);
    }

    public void deleteQuizSubmission(Long id) {
        quizSubmissionRepository.deleteById(id);
    }
}