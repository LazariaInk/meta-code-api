package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.model.Topic;
import com.lazaria.metacode.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/all/{topicId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    List<Chapter> getAllChaptersForSpecificTopic(@PathVariable int topicId) {
        return chapterService.getAllChaptersForSpecificTopic(topicId);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    List<Chapter> getAllChapters() {
        return chapterService.getAllChapters();
    }

    @PutMapping("/create/{topicId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    Optional<Topic> addChapterToTopic(@RequestBody Chapter newChapter, @PathVariable int topicId) {
        return chapterService.addChapterToTopic(newChapter, topicId);
    }

    @PutMapping("/edit/{chapterId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    Optional<Chapter> editChapter(@RequestBody Chapter editedChapter, @PathVariable int chapterId) {
        return chapterService.editChapter(editedChapter, chapterId);
    }

    @DeleteMapping("/delete/{chapterId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    void deleteChapterById(@PathVariable int chapterId) {
        chapterService.deleteChapterById(chapterId);
    }

}

