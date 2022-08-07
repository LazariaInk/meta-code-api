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
@RequestMapping(path = "/admin/topic")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ChapterRepository chapterRepository;

    @PostMapping("/create")
    public Topic createTopic(@RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    @GetMapping("/all")
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }
}
