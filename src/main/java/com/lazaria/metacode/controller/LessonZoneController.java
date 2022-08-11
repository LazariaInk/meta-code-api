package com.lazaria.metacode.controller;

import com.lazaria.metacode.dto.Lesson;
import com.lazaria.metacode.dto.LessonZone;
import com.lazaria.metacode.service.LessonZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/lessonZone")
public class LessonZoneController {

    @Autowired
    LessonZoneService lessonZoneService;

    @GetMapping("/all/{lessonId}")
    List<LessonZone> getAllLessonZonesForSpecificLesson(@PathVariable int lessonId) {
        return lessonZoneService.getAllLessonsZoneForSpecificLesson(lessonId);
    }

    @PutMapping("/create/{lessonId}")
    Optional<Lesson> addLessonZoneToLesson(@RequestBody LessonZone newLessonZone, @PathVariable int lessonId) {
        return lessonZoneService.addLessonZoneToLesson(newLessonZone, lessonId);
    }

    @PutMapping("/edit/{lessonZoneId}")
    Optional<LessonZone> editLesson(@RequestBody LessonZone editedLessonZone, @PathVariable int lessonZoneId) {
        return lessonZoneService.editLessonZone(editedLessonZone, lessonZoneId);
    }

    @DeleteMapping("/delete/{lessonZoneId}")
    void deleteLessonById(@PathVariable int lessonZoneId) {
        lessonZoneService.deleteLessonZoneById(lessonZoneId);
    }

}


