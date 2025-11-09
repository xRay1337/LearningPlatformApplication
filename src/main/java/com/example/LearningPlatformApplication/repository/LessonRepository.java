package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByModuleId(Long moduleId);
    List<Lesson> findByModuleCourseId(Long courseId);
}