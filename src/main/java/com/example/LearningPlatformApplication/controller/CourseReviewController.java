package com.example.LearningPlatformApplication.controller;

import com.example.LearningPlatformApplication.entity.CourseReview;
import com.example.LearningPlatformApplication.service.CourseReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class CourseReviewController {
    private final CourseReviewService courseReviewService;

    @PostMapping
    public ResponseEntity<CourseReview> createReview(@RequestBody CourseReview review) {
        return ResponseEntity.ok(courseReviewService.createReview(review));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReview> getReviewById(@PathVariable Long id) {
        return courseReviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseReview>> getReviewsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseReviewService.getReviewsByCourse(courseId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseReview>> getReviewsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(courseReviewService.getReviewsByStudent(studentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReview> updateReview(@PathVariable Long id, @RequestBody CourseReview review) {
        review.setId(id);
        return ResponseEntity.ok(courseReviewService.updateReview(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        courseReviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}