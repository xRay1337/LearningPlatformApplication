package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.Category;
import com.example.LearningPlatformApplication.entity.Course;
import com.example.LearningPlatformApplication.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryIntegrationTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private User teacher;
    private Category category;

    @BeforeEach
    void setUp() {
        teacher = new User();
        teacher.setName("Teacher");
        teacher.setEmail("teacher@example.com");
        teacher.setRole("TEACHER");
        teacher = userRepository.save(teacher);

        category = new Category();
        category.setName("Programming");
        category = categoryRepository.save(category);
    }

    @Test
    void whenFindByCategory_thenReturnCourses() {
        // Given
        Course course = new Course();
        course.setTitle("Java Course");
        course.setDescription("Java programming");
        course.setTeacher(teacher);
        course.setCategory(category);
        course.setStartDate(LocalDate.now());
        courseRepository.save(course);

        // When
        List<Course> courses = courseRepository.findByCategoryId(category.getId());

        // Then
        assertFalse(courses.isEmpty());
        assertEquals("Java Course", courses.get(0).getTitle());
    }

    @Test
    void whenFindByTeacher_thenReturnCourses() {
        // Given
        Course course = new Course();
        course.setTitle("Spring Course");
        course.setDescription("Spring Framework");
        course.setTeacher(teacher);
        course.setCategory(category);
        course.setStartDate(LocalDate.now());
        courseRepository.save(course);

        // When
        List<Course> courses = courseRepository.findByTeacherId(teacher.getId());

        // Then
        assertFalse(courses.isEmpty());
        assertEquals("Spring Course", courses.get(0).getTitle());
    }

    @Test
    void whenSearchByTitle_thenReturnCourses() {
        // Given
        Course course = new Course();
        course.setTitle("Advanced Java Programming");
        course.setDescription("Advanced Java topics");
        course.setTeacher(teacher);
        course.setCategory(category);
        course.setStartDate(LocalDate.now());
        courseRepository.save(course);

        // When
        List<Course> courses = courseRepository.findByTitleContainingIgnoreCase("java");

        // Then
        assertFalse(courses.isEmpty());
        assertEquals("Advanced Java Programming", courses.get(0).getTitle());
    }
}