package com.lazaria.metacode.service;


import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.model.Lesson;
import com.lazaria.metacode.repository.ChapterRepository;
import com.lazaria.metacode.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ChapterRepository chapterRepository;

    public List<Lesson> getAllLessonsForSpecificChapter(int chapterId) {
        return chapterRepository.findById(chapterId).get().getLessons();
    }

    public Optional<Chapter> addLessonToChapter(Lesson newLesson, int chapterId) {

        lessonRepository.save(newLesson);

        return chapterRepository.findById(chapterId)
                .map(chapter -> {
                    chapter.getLessons().add(newLesson);
                    return chapterRepository.save(chapter);
                });
    }

    public Optional<Lesson> editLesson(Lesson editedLesson, int lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lesson.setLessonName(editedLesson.getLessonName());
                    return lessonRepository.save(lesson);
                });
    }

    public void deleteLessonById(int lessonId) {
        lessonRepository.deleteById((lessonId));
    }

    public void insertLessonContent(int lessonId, String lessonContent) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isPresent()) {
            Lesson lessonToUpdate = lesson.get();
            lessonToUpdate.setLessonContent(lessonContent);
            lessonRepository.save(lessonToUpdate);
        }
    }
}

