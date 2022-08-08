package com.lazaria.metacode.controller;

import com.lazaria.metacode.dto.Topic;
import com.lazaria.metacode.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/create")
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @GetMapping("/all")
    public List<Topic> findAllTopics() {
        return topicService.findAllTopics();
    }

    @DeleteMapping("/delete/{topicId}")
    public void deleteTopic(@PathVariable int topicId) {
        topicService.deleteTopic(topicId);
    }

    @PutMapping("edit/{topicId}")
    public Optional<Topic> editeTopicName(@RequestBody Topic topicForEdit, @PathVariable int topicId) {
        return topicService.editeTopicName(topicForEdit, topicId);
    }
}

