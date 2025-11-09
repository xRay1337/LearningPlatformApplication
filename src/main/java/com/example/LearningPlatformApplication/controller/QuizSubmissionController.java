package com.example.LearningPlatformApplication.controller;

import com.example.LearningPlatformApplication.entity.QuizSubmission;
import com.example.LearningPlatformApplication.service.QuizSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-submissions")
@RequiredArgsConstructor
public class QuizSubmissionController {
    private final QuizSubmissionService quizSubmissionService;

    @PostMapping
    public ResponseEntity<QuizSubmission> submitQuiz(@RequestBody QuizSubmission quizSubmission) {
        return ResponseEntity.ok(quizSubmissionService.submitQuiz(quizSubmission));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizSubmission> getQuizSubmissionById(@PathVariable Long id) {
        return quizSubmissionService.getQuizSubmissionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizSubmission>> getQuizSubmissionsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizSubmissionService.getQuizSubmissionsByQuiz(quizId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<QuizSubmission>> getQuizSubmissionsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(quizSubmissionService.getQuizSubmissionsByStudent(studentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizSubmission(@PathVariable Long id) {
        quizSubmissionService.deleteQuizSubmission(id);
        return ResponseEntity.ok().build();
    }
}