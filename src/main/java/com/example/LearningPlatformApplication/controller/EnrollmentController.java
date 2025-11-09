package com.example.LearningPlatformApplication.controller;

import com.example.LearningPlatformApplication.entity.Enrollment;
import com.example.LearningPlatformApplication.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollUser(@RequestParam Long userId, @RequestParam Long courseId) {
        return ResponseEntity.ok(enrollmentService.enrollUserInCourse(userId, courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getUserEnrollments(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.getUserEnrollments(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getCourseEnrollments(courseId));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> completeEnrollment(@PathVariable Long id) {
        enrollmentService.completeEnrollment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkEnrollment(@RequestParam Long userId, @RequestParam Long courseId) {
        return ResponseEntity.ok(enrollmentService.isUserEnrolledInCourse(userId, courseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok().build();
    }
}