package com.example.LearningPlatformApplication.service;

import com.example.LearningPlatformApplication.entity.*;
import com.example.LearningPlatformApplication.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private CourseReviewService courseReviewService;

    private User student;
    private User teacher;
    private Course course;

    @BeforeEach
    void setUp() {
        // Create teacher
        teacher = new User();
        teacher.setName("Integration Teacher");
        teacher.setEmail("integration.teacher@example.com");
        teacher.setRole("TEACHER");
        teacher = userService.createUser(teacher);

        // Create student
        student = new User();
        student.setName("Integration Student");
        student.setEmail("integration.student@example.com");
        student.setRole("STUDENT");
        student = userService.createUser(student);

        // Create category
        Category category = new Category();
        category.setName("Integration Testing");
        // Note: You might need to inject CategoryRepository for this

        // Create course
        course = new Course();
        course.setTitle("Integration Test Course");
        course.setDescription("Course for integration testing");
        course.setTeacher(teacher);
        course.setStartDate(LocalDate.now());
        course = courseService.createCourse(course);
    }

    @Test
    void testUserCourseEnrollmentFlow() {
        // When
        Enrollment enrollment = enrollmentService.enrollUserInCourse(student.getId(), course.getId());

        // Then
        assertNotNull(enrollment.getId());
        assertEquals("ACTIVE", enrollment.getStatus());
        assertTrue(enrollmentService.isUserEnrolledInCourse(student.getId(), course.getId()));
    }

    @Test
    void testCourseReviewFlow() {
        // Given
        enrollmentService.enrollUserInCourse(student.getId(), course.getId());

        CourseReview review = new CourseReview();
        review.setCourse(course);
        review.setStudent(student);
        review.setRating(5);
        review.setComment("Excellent course!");

        // When
        CourseReview savedReview = courseReviewService.createReview(review);

        // Then
        assertNotNull(savedReview.getId());
        assertEquals(5, savedReview.getRating());
        assertTrue(courseReviewService.hasStudentReviewedCourse(course.getId(), student.getId()));
    }

    @Test
    void testUserRetrieval() {
        // When
        Optional<User> foundUser = userService.getUserByEmail("integration.student@example.com");

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("Integration Student", foundUser.get().getName());
    }
}