package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EnrollmentRepositoryIntegrationTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private User student;
    private User teacher;
    private Course course;

    @BeforeEach
    void setUp() {
        teacher = new User();
        teacher.setName("Teacher");
        teacher.setEmail("teacher@example.com");
        teacher.setRole("TEACHER");
        teacher = userRepository.save(teacher);

        student = new User();
        student.setName("Student");
        student.setEmail("student@example.com");
        student.setRole("STUDENT");
        student = userRepository.save(student);

        Category category = new Category();
        category.setName("Programming");
        category = categoryRepository.save(category);

        course = new Course();
        course.setTitle("Test Course");
        course.setDescription("Test Description");
        course.setTeacher(teacher);
        course.setCategory(category);
        course.setStartDate(LocalDate.now());
        course = courseRepository.save(course);
    }

    @Test
    void whenEnrollUser_thenEnrollmentIsCreated() {
        // Given
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");

        // When
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        // Then
        assertNotNull(savedEnrollment.getId());
        assertEquals("ACTIVE", savedEnrollment.getStatus());
    }

    @Test
    void whenFindByUser_thenReturnEnrollments() {
        // Given
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");
        enrollmentRepository.save(enrollment);

        // When
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(student.getId());

        // Then
        assertFalse(enrollments.isEmpty());
        assertEquals(student.getId(), enrollments.get(0).getUser().getId());
    }

    @Test
    void whenCheckEnrollmentExists_thenReturnTrue() {
        // Given
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");
        enrollmentRepository.save(enrollment);

        // When
        boolean exists = enrollmentRepository.existsByUserIdAndCourseId(student.getId(), course.getId());

        // Then
        assertTrue(exists);
    }

    @Test
    void whenFindByUserAndCourse_thenReturnEnrollment() {
        // Given
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");
        enrollmentRepository.save(enrollment);

        // When
        Optional<Enrollment> foundEnrollment = enrollmentRepository.findByUserIdAndCourseId(student.getId(), course.getId());

        // Then
        assertTrue(foundEnrollment.isPresent());
        assertEquals("ACTIVE", foundEnrollment.get().getStatus());
    }
}