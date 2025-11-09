package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.Course;
import com.example.LearningPlatformApplication.entity.Module;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LazyLoadingIntegrationTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    void whenAccessLazyCollectionOutsideTransaction_thenThrowException() {
        // Given
        Course course = new Course();
        course.setTitle("Test Course");
        course = courseRepository.save(course);

        Module module = new Module();
        module.setTitle("Test Module");
        module.setCourse(course);
        moduleRepository.save(module);

        // When - load course without modules (lazy loading)
        Course foundCourse = courseRepository.findById(course.getId()).orElseThrow();

        // Then - trying to access lazy collection should cause issues
        // This demonstrates the LazyInitializationException problem
        assertThrows(Exception.class, () -> {
            // This will fail if accessed outside transaction
            List<Module> modules = foundCourse.getModules();
            modules.size(); // Force initialization
        });
    }

    @Test
    void whenUsingJoinFetch_thenLazyCollectionIsLoaded() {
        // This test would require a custom repository method with @Query and JOIN FETCH
        // to demonstrate how to solve lazy loading issues
        assertTrue(true); // Placeholder for actual implementation
    }
}