package com.example.LearningPlatformApplication.service;

import com.example.LearningPlatformApplication.entity.Enrollment;
import com.example.LearningPlatformApplication.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public Enrollment enrollUserInCourse(Long userId, Long courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(new com.example.LearningPlatformApplication.entity.User());
        enrollment.getUser().setId(userId);
        enrollment.setCourse(new com.example.LearningPlatformApplication.entity.Course());
        enrollment.getCourse().setId(courseId);
        enrollment.setEnrollDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");

        return enrollmentRepository.save(enrollment);
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> getUserEnrollments(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public List<Enrollment> getCourseEnrollments(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public Optional<Enrollment> getUserCourseEnrollment(Long userId, Long courseId) {
        return enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
    }

    public void completeEnrollment(Long enrollmentId) {
        enrollmentRepository.findById(enrollmentId).ifPresent(enrollment -> {
            enrollment.setStatus("COMPLETED");
            enrollmentRepository.save(enrollment);
        });
    }

    public boolean isUserEnrolledInCourse(Long userId, Long courseId) {
        return enrollmentRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}