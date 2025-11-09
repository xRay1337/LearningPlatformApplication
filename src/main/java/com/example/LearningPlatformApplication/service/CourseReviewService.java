package com.example.LearningPlatformApplication.service;

import com.example.LearningPlatformApplication.entity.CourseReview;
import com.example.LearningPlatformApplication.repository.CourseReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseReviewService {
    private final CourseReviewRepository courseReviewRepository;

    public CourseReview createReview(CourseReview review) {
        review.setCreatedAt(LocalDateTime.now());
        return courseReviewRepository.save(review);
    }

    public Optional<CourseReview> getReviewById(Long id) {
        return courseReviewRepository.findById(id);
    }

    public List<CourseReview> getReviewsByCourse(Long courseId) {
        return courseReviewRepository.findByCourseId(courseId);
    }

    public List<CourseReview> getReviewsByStudent(Long studentId) {
        return courseReviewRepository.findByStudentId(studentId);
    }

    public Optional<CourseReview> getStudentReviewForCourse(Long courseId, Long studentId) {
        return courseReviewRepository.findByCourseIdAndStudentId(courseId, studentId);
    }

    public boolean hasStudentReviewedCourse(Long courseId, Long studentId) {
        return courseReviewRepository.existsByCourseIdAndStudentId(courseId, studentId);
    }

    public CourseReview updateReview(CourseReview review) {
        return courseReviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        courseReviewRepository.deleteById(id);
    }
}