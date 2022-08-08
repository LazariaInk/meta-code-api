package com.lazaria.metacode.controller;

import com.lazaria.metacode.dto.Chapter;
import com.lazaria.metacode.dto.Topic;
import com.lazaria.metacode.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/chapter/all/{topicId}")
    List<Chapter> getAllChaptersForSpecificTopic(@PathVariable int topicId) {
        return chapterService.getAllChaptersForSpecificTopic(topicId);
    }

    @PutMapping("/chapter/create/{topicId}")
    Optional<Topic> addChapterToTopic(@RequestBody Chapter newChapter, @PathVariable int topicId) {
        return chapterService.addChapterToTopic(newChapter, topicId);
    }

    @PutMapping("/chapter/edit/{chapterId}")
    Optional<Chapter> editChapter(@RequestBody Chapter editedChapter, @PathVariable int chapterId) {
        return chapterService.editChapter(editedChapter, chapterId);
    }

    @DeleteMapping("/chapter/delete/{chapterId}")
    void deleteChapterFromSpecificTopic(@PathVariable int chapterId) {
        chapterService.deleteChapterFromSpecificTopic(chapterId);
    }

}

