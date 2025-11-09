package com.example.LearningPlatformApplication.service;

import com.example.LearningPlatformApplication.entity.Submission;
import com.example.LearningPlatformApplication.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public Submission submitAssignment(Submission submission) {
        submission.setSubmittedAt(LocalDateTime.now());
        return submissionRepository.save(submission);
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public List<Submission> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    public List<Submission> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public Optional<Submission> getStudentSubmissionForAssignment(Long assignmentId, Long studentId) {
        return submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }

    public Submission gradeSubmission(Long submissionId, Integer score, String feedback) {
        return submissionRepository.findById(submissionId).map(submission -> {
            submission.setScore(score);
            submission.setFeedback(feedback);
            return submissionRepository.save(submission);
        }).orElse(null);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}