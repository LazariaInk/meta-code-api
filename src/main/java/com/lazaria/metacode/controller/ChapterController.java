package com.lazaria.metacode.controller;

import com.lazaria.metacode.dto.Chapter;
import com.lazaria.metacode.dto.Topic;
import com.lazaria.metacode.repository.ChapterRepository;
import com.lazaria.metacode.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin")
public class ChapterController {
    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/chapter/all/{topicId}")
    List<Chapter> getAllChaptersForSpecificTopic(@PathVariable int topicId) {
        return topicRepository.findById(topicId).get().getChapters();
    }

    @PutMapping("/chapter/create/{topicId}")
    Optional<Topic> addChapterToTopic(@RequestBody Chapter newChapter, @PathVariable int topicId) {

        chapterRepository.save(newChapter);

        return topicRepository.findById(topicId)
                .map(topic -> {
                    topic.getChapters().add(newChapter);
                    return topicRepository.save(topic);
                });
    }

    @DeleteMapping("/chapter/delete/{chapterId}")
    void deleteChapterFromSpecificTopic(@PathVariable int chapterId, @PathVariable int topicId) {
       chapterRepository.deleteById((chapterId));
    }

}

