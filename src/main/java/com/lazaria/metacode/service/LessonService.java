package com.lazaria.metacode.service;


import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.model.Image;
import com.lazaria.metacode.model.Lesson;
import com.lazaria.metacode.model.dao.ImageDTO;
import com.lazaria.metacode.repository.ChapterRepository;
import com.lazaria.metacode.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public void insertLessonContent(int lessonId, String lessonContent, List<ImageDTO> imagesMap) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (lessonOptional.isPresent()) {
            Lesson lessonToUpdate = lessonOptional.get();
            lessonToUpdate.setLessonContent(lessonContent);
            // Update images
            if (imagesMap != null && !imagesMap.isEmpty()) {
                // Clear existing images
                lessonToUpdate.getImages().clear();
                // Add new images
                for (ImageDTO imageDTO : imagesMap) {
                    Image image = new Image();
                    image.setFileName(imageDTO.getFileName());
                    image.setBase64Data(imageDTO.getBase64Data());
                    image.setLesson(lessonToUpdate);
                    lessonToUpdate.getImages().add(image);
                }
            }
            lessonRepository.save(lessonToUpdate);
        }
    }

}

