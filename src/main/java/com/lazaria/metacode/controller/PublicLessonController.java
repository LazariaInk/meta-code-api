package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Lesson;
import com.lazaria.metacode.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lesson")
public class PublicLessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/{lessonId}/content")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Lesson> getLessonContent(@PathVariable int lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        return lesson.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
