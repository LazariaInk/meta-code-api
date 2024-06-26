package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Topic;
import com.lazaria.metacode.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Topic> findAllTopics() {
        return topicService.findAllTopics();
    }

    @DeleteMapping("/delete/{topicId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteTopic(@PathVariable int topicId) {
        topicService.deleteTopic(topicId);
    }

    @PutMapping("edit/{topicId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Optional<Topic> editeTopicName(@RequestBody Topic topicForEdit, @PathVariable int topicId) {
        return topicService.editeTopicName(topicForEdit, topicId);
    }
}
