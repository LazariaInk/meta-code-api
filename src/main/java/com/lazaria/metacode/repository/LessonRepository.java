package com.lazaria.metacode.repository;

import com.lazaria.metacode.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
