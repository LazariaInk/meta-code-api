package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Lesson;
import com.lazaria.metacode.model.dao.ImageDTO;
import com.lazaria.metacode.model.dao.LessonContentDTO;
import com.lazaria.metacode.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lesson")
public class PublicLessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/{lessonId}/content")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<LessonContentDTO> getLessonContent(@PathVariable int lessonId) {
        Optional<Lesson> lessonOpt = lessonRepository.findById(lessonId);
        return lessonOpt.map(lesson -> {
            LessonContentDTO dto = new LessonContentDTO();
            dto.setLessonContent(lesson.getLessonContent());
            List<ImageDTO> imageDTOs = lesson.getImages().stream()
                    .map(image -> new ImageDTO(image.getFileName(), image.getBase64Data()))
                    .collect(Collectors.toList());
            dto.setImages(imageDTOs);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
