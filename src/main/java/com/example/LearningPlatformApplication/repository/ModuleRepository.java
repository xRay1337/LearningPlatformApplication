package com.example.LearningPlatformApplication.repository;

import com.example.LearningPlatformApplication.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourseId(Long courseId);
    List<Module> findByCourseIdOrderByOrderIndex(Long courseId);
}