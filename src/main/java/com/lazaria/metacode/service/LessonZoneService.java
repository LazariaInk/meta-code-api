package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Lesson;
import com.lazaria.metacode.dto.LessonZone;
import com.lazaria.metacode.repository.LessonRepository;
import com.lazaria.metacode.repository.LessonZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonZoneService {

    @Autowired
    LessonZoneRepository lessonZoneRepository;

    @Autowired
    LessonRepository lessonRepository;

    public List<LessonZone> getAllLessonsZoneForSpecificLesson(int lessonId) {
        return lessonRepository.findById(lessonId).get().getLessonZones();
    }

    public Optional<Lesson> addLessonZoneToLesson(LessonZone newLessonZone, int lessonId) {

        lessonZoneRepository.save(newLessonZone);

        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lesson.getLessonZones().add(newLessonZone);
                    return lessonRepository.save(lesson);
                });
    }

    public Optional<LessonZone> editLessonZone(LessonZone editedLessonZone, int lessonZoneId) {
        return lessonZoneRepository.findById(lessonZoneId)
                .map(lessonZone -> {
                    lessonZone.setLessonZoneType(editedLessonZone.getLessonZoneType());
                    return lessonZoneRepository.save(lessonZone);
                });
    }

    public void deleteLessonZoneById(int lessonZoneId) {
        lessonZoneRepository.deleteById((lessonZoneId));
    }

}

