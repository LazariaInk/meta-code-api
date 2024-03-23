package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.model.Lesson;
import com.lazaria.metacode.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("/all/{chapterId}")
    @CrossOrigin(origins = "http://localhost:3000")
    List<Lesson> getAllLessonsForSpecificChapter(@PathVariable int chapterId) {
        return lessonService.getAllLessonsForSpecificChapter(chapterId);
    }

    @PutMapping("/create/{chapterId}")
    @CrossOrigin(origins = "http://localhost:3000")
    Optional<Chapter> addLessonToChapter(@RequestBody Lesson newLesson, @PathVariable int chapterId) {
        return lessonService.addLessonToChapter(newLesson, chapterId);
    }

    @PutMapping("/edit/{lessonId}")
    @CrossOrigin(origins = "http://localhost:3000")
    Optional<Lesson> editLesson(@RequestBody Lesson editedLesson, @PathVariable int lessonId) {
        return lessonService.editLesson(editedLesson, lessonId);
    }

    @DeleteMapping("/delete/{lessonId}")
    @CrossOrigin(origins = "http://localhost:3000")
    void deleteLessonById(@PathVariable int lessonId) {
        lessonService.deleteLessonById(lessonId);
    }

    @PutMapping("/{lessonId}/insert")
    @CrossOrigin(origins = "http://localhost:3000")
    void insertContentInLesson(@RequestBody String lessonContent, @PathVariable int lessonId){
        lessonService.insertLessonContent(lessonId, lessonContent);
    }

}
