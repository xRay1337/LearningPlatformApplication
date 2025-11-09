package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.*;
import com.example.LearningPlatformApplication.entity.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubmissionRepositoryIntegrationTest {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private User student;
    private Assignment assignment;

    @BeforeEach
    void setUp() {
        // Create teacher
        User teacher = new User();
        teacher.setName("Teacher");
        teacher.setEmail("teacher@example.com");
        teacher.setRole("TEACHER");
        teacher = userRepository.save(teacher);

        // Create student
        student = new User();
        student.setName("Student");
        student.setEmail("student@example.com");
        student.setRole("STUDENT");
        student = userRepository.save(student);

        // Create category
        Category category = new Category();
        category.setName("Programming");
        category = categoryRepository.save(category);

        // Create course
        Course course = new Course();
        course.setTitle("Test Course");
        course.setDescription("Test Description");
        course.setTeacher(teacher);
        course.setCategory(category);
        course = courseRepository.save(course);

        // Create module
        Module module = new Module();
        module.setTitle("Test Module");
        module.setCourse(course);
        module = moduleRepository.save(module);

        // Create lesson
        Lesson lesson = new Lesson();
        lesson.setTitle("Test Lesson");
        lesson.setModule(module);
        lesson = lessonRepository.save(lesson);

        // Create assignment
        assignment = new Assignment();
        assignment.setTitle("Test Assignment");
        assignment.setDescription("Test Description");
        assignment.setLesson(lesson);
        assignment.setMaxScore(100);
        assignment = assignmentRepository.save(assignment);
    }

    @Test
    void whenSubmitAssignment_thenSubmissionIsSaved() {
        // Given
        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setContent("Test submission content");
        submission.setSubmittedAt(LocalDateTime.now());

        // When
        Submission savedSubmission = submissionRepository.save(submission);

        // Then
        assertNotNull(savedSubmission.getId());
        assertEquals("Test submission content", savedSubmission.getContent());
    }

    @Test
    void whenFindByAssignment_thenReturnSubmissions() {
        // Given
        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setContent("Test submission");
        submission.setSubmittedAt(LocalDateTime.now());
        submissionRepository.save(submission);

        // When
        List<Submission> submissions = submissionRepository.findByAssignmentId(assignment.getId());

        // Then
        assertFalse(submissions.isEmpty());
        assertEquals(assignment.getId(), submissions.get(0).getAssignment().getId());
    }

    @Test
    void whenFindByStudentAndAssignment_thenReturnSubmission() {
        // Given
        Submission submission = new Submission();
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setContent("Test submission");
        submission.setSubmittedAt(LocalDateTime.now());
        submissionRepository.save(submission);

        // When
        Optional<Submission> foundSubmission = submissionRepository.findByAssignmentIdAndStudentId(
                assignment.getId(), student.getId());

        // Then
        assertTrue(foundSubmission.isPresent());
        assertEquals("Test submission", foundSubmission.get().getContent());
    }
}