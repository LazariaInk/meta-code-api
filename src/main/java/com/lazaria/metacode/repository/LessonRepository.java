package com.lazaria.metacode.repository;

import com.lazaria.metacode.dto.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
