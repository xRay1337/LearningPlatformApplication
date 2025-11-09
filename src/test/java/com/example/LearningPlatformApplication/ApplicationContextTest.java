package com.example.LearningPlatformApplication;

import com.example.LearningPlatformApplication.controller.CourseController;
import com.example.LearningPlatformApplication.controller.UserController;
import com.example.LearningPlatformApplication.repository.CourseRepository;
import com.example.LearningPlatformApplication.repository.UserRepository;
import com.example.LearningPlatformApplication.service.CourseService;
import com.example.LearningPlatformApplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationContextTest {

    @Autowired
    private UserController userController;

    @Autowired
    private CourseController courseController;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void contextLoads() {
        // Verify that Spring context loads successfully
        assertNotNull(userController);
        assertNotNull(courseController);
        assertNotNull(userService);
        assertNotNull(courseService);
        assertNotNull(userRepository);
        assertNotNull(courseRepository);
    }

    @Test
    void applicationStarts() {
        LearningPlatformApplication.main(new String[] {});
        // If we reach here, application started successfully
        assertTrue(true);
    }

    private void assertTrue(boolean b) {
        // Simple assertion method
        org.junit.jupiter.api.Assertions.assertTrue(b);
    }
}