package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.model.Lesson;
import com.lazaria.metacode.model.dao.LessonContentDTO;
import com.lazaria.metacode.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/lesson")
@CrossOrigin(origins = "https://www.fabricadecoduri.com/")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("/all/{chapterId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    List<Lesson> getAllLessonsForSpecificChapter(@PathVariable int chapterId) {
        return lessonService.getAllLessonsForSpecificChapter(chapterId);
    }

    @PutMapping("/create/{chapterId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    Optional<Chapter> addLessonToChapter(@RequestBody Lesson newLesson, @PathVariable int chapterId) {
        return lessonService.addLessonToChapter(newLesson, chapterId);
    }

    @PutMapping("/edit/{lessonId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    Optional<Lesson> editLesson(@RequestBody Lesson editedLesson, @PathVariable int lessonId) {
        return lessonService.editLesson(editedLesson, lessonId);
    }

    @DeleteMapping("/delete/{lessonId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    void deleteLessonById(@PathVariable int lessonId) {
        lessonService.deleteLessonById(lessonId);
    }

    @PutMapping("/{lessonId}/insert")
    public ResponseEntity<?> insertContentInLesson(@RequestBody LessonContentDTO lessonContentDTO,
                                                   @PathVariable int lessonId) {
        lessonService.insertLessonContent(lessonId, lessonContentDTO.getLessonContent(), lessonContentDTO.getImages());
        return ResponseEntity.ok().build();
    }


}
